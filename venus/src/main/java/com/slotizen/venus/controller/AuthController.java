package com.slotizen.venus.controller;

import com.slotizen.venus.dto.*;
import com.slotizen.venus.model.OtpToken;
import com.slotizen.venus.model.User;
import com.slotizen.venus.security.JwtService;
import com.slotizen.venus.service.UserService;
import com.slotizen.venus.service.AuthService;
import com.slotizen.venus.service.OtpService;
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
    private AuthService authService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private SocialAuthService socialAuthService;
    
    @Autowired private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@Validated @RequestBody RegisterRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new JwtResponse(false, "Validation failed", null, null, null));
        }
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request) {
        try {
            boolean verified = userService.verifyOtp(request);
            if (verified) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "OTP verified successfully");
                
               /* String accessToken = jwtService.generateToken(request.getEmail(), false);
                String refreshToken = jwtService.generateToken(request.getEmail(), true);
                response.put("accessToken", accessToken);
                response.put("refreshToken", refreshToken);*/
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Invalid or expired OTP");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            logger.error("Unexpected error during OTP verification", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@RequestBody OtpRequest request) {
        try {
            // Try to find the user by email
            User user = userService.findByEmail(request.getEmail());
            if (user == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "User not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            // Generate and send a new OTP
            boolean sent = false;
            try {
                OtpToken otpToken = otpService.generateOtp(user, com.slotizen.venus.model.OtpToken.OtpType.REGISTRATION);
                sent = otpService.sendOtpEmail(user, otpToken.getOtp());
            } catch (Exception e) {
                logger.error("Failed to resend OTP for user: {}", user.getEmail(), e);
            }

            Map<String, Object> response = new HashMap<>();
            if (sent) {
                response.put("success", true);
                response.put("message", "OTP resent successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Failed to resend OTP");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            logger.error("Unexpected error during OTP resend", e);
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(authService.refresh(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(@RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(authService.logout(request.getRefreshToken()));
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
