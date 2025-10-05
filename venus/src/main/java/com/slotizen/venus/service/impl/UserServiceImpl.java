package com.slotizen.venus.service.impl;

import com.slotizen.venus.dto.*;
import com.slotizen.venus.model.OtpToken;
import com.slotizen.venus.model.User;
import com.slotizen.venus.dto.UserBusinessDto;
import com.slotizen.venus.repository.UserRepository;
import com.slotizen.venus.repository.UserBusinessRepository;
import com.slotizen.venus.service.OtpService;
import com.slotizen.venus.service.UserService;
import com.slotizen.venus.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBusinessRepository userBusinessRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        try {
            logger.info("Starting registration for email: {}", request.getEmail());
            // Check if user already exists
            if (existsByEmail(request.getEmail())) {
                logger.warn("Registration failed: email already registered: {}", request.getEmail());
                return RegisterResponse.failure("Email already registered");
            }

            logger.info("Creating new user object for email: {}", request.getEmail());
            User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone(),
                passwordEncoder.encode(request.getPassword())
            );

            logger.info("Saving user to repository for email: {}", user.getEmail());
            user = userRepository.save(user);

            logger.info("Generating OTP for user: {}", user.getEmail());
            OtpToken otpToken = otpService.generateOtp(user, OtpToken.OtpType.REGISTRATION);

            logger.info("Sending OTP email to user: {}", user.getEmail());
            boolean emailSent = otpService.sendOtpEmail(user, otpToken.getOtp());

            if (!emailSent) {
                logger.warn("Failed to send OTP email for user: {}", user.getEmail());
                return RegisterResponse.failure("Registration successful but failed to send verification email");
            }

            RegisterResponse.RegisterResponseData data = new RegisterResponse.RegisterResponseData(
                "user_" + user.getId(),
                user.getEmail(),
                true,
                "email"
            );

            logger.info("User registered successfully: {}", user.getEmail());
            return RegisterResponse.success("Registration successful. OTP sent.", data);

        } catch (DataIntegrityViolationException e) {
            logger.error("Email already exists: {}", request.getEmail(), e);
            return RegisterResponse.failure("Email already registered");
        } catch (Exception e) {
            logger.error("Unexpected error during registration for email: {}. Exception: {}", request.getEmail(), e.getMessage(), e);
            Throwable cause = e.getCause();
            while (cause != null) {
                logger.error("Caused by: {}: {}", cause.getClass().getName(), cause.getMessage());
                cause = cause.getCause();
            }
            return RegisterResponse.failure("Unexpected error during registration");
        }
    }

    @Override
    public boolean verifyOtp(OtpRequest request) {
        if (request == null || request.getEmail() == null || request.getOtp() == null) {
            logger.warn("OTP verification failed: missing email or otp");
            return false;
        }
        return otpService.validateOtp(request.getEmail(), request.getOtp(), OtpToken.OtpType.REGISTRATION);
    }

    @Override
    public String login(LoginRequest request) {
        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            logger.warn("Login failed: missing username/email or password");
            return null;
        }
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            // Try username if not found by email (if you have username field)
            // userOpt = userRepository.findByUsername(request.getUsernameOrEmail());
            logger.warn("Login failed: user not found for {}", request.getEmail());
            return null;
        }
        User user = userOpt.get();
        if (!user.isEnabled()) {
            logger.warn("Login failed: user not enabled for {}", user.getEmail());
            return null;
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            logger.warn("Login failed: invalid password for {}", user.getEmail());
            return null;
        }
        // Generate JWT token
        String token = com.slotizen.venus.security.JwtUtil.generateToken(user.getEmail());
        logger.info("Login successful for user: {}", user.getEmail());
        return token;
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {
        // TODO: Implement forgot password logic
    }

    @Override
    public boolean resetPassword(ResetPasswordRequest request) {
        // TODO: Implement reset password logic
        return false;
    }

    @Override
    public UserProfileResponse getCurrentUserProfile() {
        // TODO: Implement get current user profile logic
        return null;
    }

    @Override
    public UserProfileResponse getUserById(Long userId) {
        // TODO: Implement get user by ID logic
        return null;
    }

    @Override
    public List<UserProfileResponse> getAllUsers() {
        // TODO: Implement get all users logic
        return null;
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    
    @Override
    public List<UserBusinessDto> getUserBusinesses() {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        
        // Use the new repository method that includes business name in single query
        return userBusinessRepository.findUserBusinessesWithNameByUserId(currentUserId);
    }
}
