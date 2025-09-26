package com.slotizen.venus.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class JwtService {
	
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access-token-expiration-ms}")
	private long accessTokenExpiration;

	@Value("${jwt.refresh-token-expiration-ms}")
	private long refreshTokenExpiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username, boolean isRefresh) {
        long expiration = isRefresh ? refreshTokenExpiration : accessTokenExpiration;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * New: Generate token with embedded user id + roles.
     */
    public String generateToken(Long userId, String email, Collection<String> roles, boolean isRefresh) {
        long expiration = isRefresh ? refreshTokenExpiration : accessTokenExpiration;
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", userId);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }

    public String getUsernameFromToken(String token) {
        return parse(token).getBody().getSubject();
    }

    public Long getUserIdFromToken(String token) {
        Object uid = parse(token).getBody().get("uid");
        if (uid == null) return null;
        if (uid instanceof Number n) return n.longValue();
        return Long.valueOf(uid.toString());
    }

    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        Object r = parse(token).getBody().get("roles");
        if (r instanceof List<?> list) {
            return list.stream().map(Object::toString).toList();
        }
        return Collections.emptyList();
    }

    public boolean validateToken(String token) {
        try {
            parse(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}