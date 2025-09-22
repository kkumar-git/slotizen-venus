package com.slotizen.venus.service;

import org.springframework.http.ResponseEntity;

public interface SocialAuthService {
    ResponseEntity<?> redirectToProvider(String provider);
    ResponseEntity<?> handleProviderCallback(String provider, String code);
}
