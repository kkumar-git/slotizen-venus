package com.slotizen.venus.controller;

import com.slotizen.venus.dto.*;
import com.slotizen.venus.service.UserService;
import com.slotizen.venus.service.SocialAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private SocialAuthService socialAuthService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest request, BindingResult bindingResult) {
        try {
            // Check for validation errors
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                
                RegisterResponse response = RegisterResponse.failure("Validation failed");
                return ResponseEntity.badRequest().body(response);
            }

            // Process registration
            RegisterResponse response = userService.register(request);
            
            if (response.isSuccess()) {
                logger.info("User registration successful for email: {}", request.getEmail());
                return ResponseEntity.ok(response);
            } else {
                logger.warn("User registration failed for email: {} - {}", request.getEmail(), response.getMessage());
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            logger.error("Unexpected error during registration", e);
            RegisterResponse response = RegisterResponse.failure("Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request) {
        try {
            boolean verified = userService.verifyOtp(request);
            if (verified) {
                return ResponseEntity.ok("OTP verified successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP");
            }
        } catch (Exception e) {
            logger.error("Unexpected error during OTP verification", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // ...implementation...
        return ResponseEntity.ok().build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        // ...implementation...
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        // ...implementation...
        return ResponseEntity.ok().build();
    }

    @GetMapping("/social/{provider}")
    public ResponseEntity<?> socialLogin(@PathVariable String provider) {
           // ...implementation...
    	System.out.println("Social login with provider: " + provider);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/social/callback")
    public ResponseEntity<?> socialCallback(@RequestParam String provider, @RequestParam String code) {
        // ...implementation...
        return ResponseEntity.ok().build();
    }
}
