package com.slotizen.venus.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.slotizen.venus.security.JwtUserPrincipal;

public final class SecurityUtils {

    private SecurityUtils(){}

    public static JwtUserPrincipal getCurrentPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new IllegalStateException("No authenticated principal");
        }
        if (auth.getPrincipal() instanceof JwtUserPrincipal p) {
            return p;
        }
        throw new IllegalStateException("Unexpected principal type: " + auth.getPrincipal().getClass());
    }

    public static Long getCurrentUserId() {
        return getCurrentPrincipal().getId();
    }

    public static String getCurrentEmail() {
        return getCurrentPrincipal().getUsername();
    }
}