package com.slotizen.venus.service;

import com.slotizen.venus.dto.*;

public interface AuthService {
    JwtResponse register(RegisterRequest request);
    JwtResponse login(LoginRequest request);
    JwtResponse refresh(TokenRefreshRequest request);
    ApiResponse<?> logout(String refreshToken);
}
