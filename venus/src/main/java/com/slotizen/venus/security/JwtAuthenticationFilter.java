package com.slotizen.venus.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

/**
 * JWT authentication filter:
 *  - Extracts Bearer token
 *  - Validates it
 *  - Loads UserDetails (currently by email/username)
 *  - Populates SecurityContext
 *
 * If you later embed userId + roles as claims in the token, you can replace
 * the UserDetailsService load with a custom principal constructed from claims.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    // Public (unauthenticated) endpoints. Could be externalized to properties.
    private static final Set<String> PUBLIC_PATH_PREFIXES = Set.of(
            "/auth/register",
            "/auth/login",
            "/auth/refresh",
            "/auth/verify-otp",
            "/auth/resend-otp"
    );

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService,
                                   UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        if (isPublicPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // If already authenticated (e.g., another filter set it), skip.
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractBearerToken(request);
        if (token != null) {
            try {
                if (jwtService.validateToken(token)) {
                    String username = jwtService.getUsernameFromToken(token);
                    if (username != null && !username.isBlank()) {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        if (userDetails != null && userDetails.isEnabled()) {
                            // Extract userId from JWT token or userDetails
                            Long userId = jwtService.getUserIdFromToken(token); // You need to implement this
                            
                            // Create JwtUserPrincipal instead of using UserDetails directly
                            JwtUserPrincipal principal = new JwtUserPrincipal(
                                    userId,
                                    userDetails.getUsername(),
                                    token,
                                    userDetails.isEnabled(),
                                    userDetails.getAuthorities()
                            );
                            
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(
                                            principal, // Use JwtUserPrincipal instead of userDetails
                                            null,
                                            principal.getAuthorities()
                                    );
                            authentication.setDetails(
                                    new WebAuthenticationDetailsSource().buildDetails(request)
                            );
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                }
            } catch (Exception ex) {
                // Optionally log at debug/warn level. Intentionally not failing the request.
                // log.warn("JWT processing failed for request {}: {}", path, ex.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        if (path == null) return false;
        for (String prefix : PUBLIC_PATH_PREFIXES) {
            if (path.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    private String extractBearerToken(HttpServletRequest request) {
        String header = request.getHeader(AUTH_HEADER);
        if (header == null || header.isBlank()) return null;
        if (!header.startsWith(BEARER_PREFIX)) return null;
        return header.substring(BEARER_PREFIX.length()).trim();
    }
}