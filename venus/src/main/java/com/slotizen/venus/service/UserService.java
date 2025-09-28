package com.slotizen.venus.service;

import com.slotizen.venus.dto.*;
import com.slotizen.venus.model.User;
import java.util.List;

public interface UserService {
    RegisterResponse register(RegisterRequest request);
    boolean verifyOtp(OtpRequest request);
    String login(LoginRequest request);
    void forgotPassword(ForgotPasswordRequest request);
    boolean resetPassword(ResetPasswordRequest request);
    UserProfileResponse getCurrentUserProfile();
    UserProfileResponse getUserById(Long userId);
    List<UserProfileResponse> getAllUsers();
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
