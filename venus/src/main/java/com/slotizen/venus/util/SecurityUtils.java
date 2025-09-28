package com.slotizen.venus.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.slotizen.venus.security.JwtUserPrincipal;

public final class SecurityUtils {

    private SecurityUtils(){}

    public static Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new IllegalStateException("No authenticated principal");
        }
        Object principal = auth.getPrincipal();

        if (principal instanceof JwtUserPrincipal) {
            return ((JwtUserPrincipal) principal).getId();
        } else if (principal instanceof UserDetails) {
            // For now, return null until JWT filter is fixed to use JwtUserPrincipal
            throw new IllegalStateException("UserDetails found instead of JwtUserPrincipal. Fix JWT authentication filter.");
        }

        throw new IllegalStateException("Unexpected principal type: " + principal.getClass());
    }

    public static String getCurrentEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new IllegalStateException("No authenticated principal");
        }
        Object principal = auth.getPrincipal();

        if (principal instanceof JwtUserPrincipal) {
            return ((JwtUserPrincipal) principal).getUsername();
        } else if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        throw new IllegalStateException("Unexpected principal type: " + principal.getClass());
    }
}