package com.slotizen.venus.service;

import com.slotizen.venus.model.OtpToken;
import com.slotizen.venus.model.User;

public interface OtpService {
    /**
     * Generate and save OTP for user
     */
    OtpToken generateOtp(User user, OtpToken.OtpType type);
    
    /**
     * Validate OTP
     */
    boolean validateOtp(String email, String otp, OtpToken.OtpType type);
    
    /**
     * Send OTP via email
     */
    boolean sendOtpEmail(User user, String otp);
    
    /**
     * Clean expired OTPs
     */
    void cleanExpiredOtps();
    
    /**
     * Invalidate all OTPs for user
     */
    void invalidateUserOtps(User user, OtpToken.OtpType type);
}