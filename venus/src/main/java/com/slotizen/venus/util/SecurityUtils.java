package com.slotizen.venus.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.slotizen.venus.security.JwtUserPrincipal;

public final class SecurityUtils {

    private SecurityUtils(){}

    public static JwtUserPrincipal getCurrentPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new IllegalStateException("No authenticated principal");
        }
        Object principal = auth.getPrincipal();

        if (principal instanceof JwtUserPrincipal) {
            return (JwtUserPrincipal) principal;
        } else if (principal instanceof UserDetails) {
            UserDetails user = (UserDetails) principal;
            // convert UserDetails to your JwtUserPrincipal if needed
            return new JwtUserPrincipal(null, user.getUsername(), null, false, user.getAuthorities());
        }

        throw new IllegalStateException("Unexpected principal type: " + principal.getClass());
    }

    public static Long getCurrentUserId() {
        return getCurrentPrincipal().getId();
    }

    public static String getCurrentEmail() {
        return getCurrentPrincipal().getUsername();
    }
}