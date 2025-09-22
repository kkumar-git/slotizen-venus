package com.slotizen.venus.service.impl;

import com.slotizen.venus.service.SocialAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SocialAuthServiceImpl implements SocialAuthService {
    @Override
    public ResponseEntity<?> redirectToProvider(String provider) {
        // TODO: Implement social login redirect logic
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> handleProviderCallback(String provider, String code) {
        // TODO: Implement social login callback logic
        return ResponseEntity.ok().build();
    }
}
