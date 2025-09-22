package com.slotizen.venus.service.impl;

import com.slotizen.venus.dto.*;
import com.slotizen.venus.model.Role;
import com.slotizen.venus.model.User;
import com.slotizen.venus.repository.RoleRepository;
import com.slotizen.venus.repository.UserRepository;
import com.slotizen.venus.security.JwtService;
import com.slotizen.venus.model.OtpToken;
import com.slotizen.venus.service.OtpService;
import com.slotizen.venus.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;
    @Autowired private OtpService otpService;

    @Override
    public JwtResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new JwtResponse(false, "Email already registered", null, null, null);
        }
        User user = new User(
            request.getFirstName(),
            request.getLastName(),
            request.getEmail(),
            request.getPhone(),
            request.getCompanyName(),
            request.getBusinessType(),
            passwordEncoder.encode(request.getPassword())
        );
        // Assign USER role by default
        Role userRole = roleRepository.findByName("ROLE_USER")
            .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        user.setEnabled(true); // Set to false if you want email verification
        userRepository.save(user);

        // Generate and send OTP after registration
        OtpToken otpToken = otpService.generateOtp(user, OtpToken.OtpType.REGISTRATION);
        boolean sent = otpService.sendOtpEmail(user, otpToken.getOtp());
        if (sent) {
            return new JwtResponse(true, "Registration successful. OTP sent to email.", null, null, null);
        } else {
            return new JwtResponse(true, "Registration successful, but failed to send OTP.", null, null, null);
        }
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            return new JwtResponse(false, "Invalid credentials", null, null, null);
        }
        User user = userOpt.get();
        if (!user.isEnabled() || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new JwtResponse(false, "Invalid credentials or user not enabled", null, null, null);
        }
        String accessToken = jwtService.generateToken(user.getEmail(), false);
        String refreshToken = jwtService.generateToken(user.getEmail(), true);
        return new JwtResponse(true, "Login successful", accessToken, refreshToken, null);
    }

    @Override
    public JwtResponse refresh(TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        if (!jwtService.validateToken(refreshToken)) {
            return new JwtResponse(false, "Invalid or expired refresh token", null, null, null);
        }
        String username = jwtService.getUsernameFromToken(refreshToken);
        String newAccessToken = jwtService.generateToken(username, false);
        String newRefreshToken = jwtService.generateToken(username, true);
        return new JwtResponse(true, "Token refreshed", newAccessToken, newRefreshToken, null);
    }

    @Override
    public ApiResponse<?> logout(String refreshToken) {
        // Optional: implement token blacklist logic here
        return new ApiResponse<>(true, "Logged out", null);
    }
}
