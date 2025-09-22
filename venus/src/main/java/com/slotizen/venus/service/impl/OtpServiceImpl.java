package com.slotizen.venus.service.impl;

import com.slotizen.venus.model.OtpToken;
import com.slotizen.venus.model.User;
import com.slotizen.venus.repository.OtpTokenRepository;
import com.slotizen.venus.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class OtpServiceImpl implements OtpService {
    private static final Logger logger = LoggerFactory.getLogger(OtpServiceImpl.class);
    private static final int OTP_LENGTH = 6;
    private static final int OTP_EXPIRY_MINUTES = 10;
    private static final SecureRandom random = new SecureRandom();

    @Autowired
    private OtpTokenRepository otpTokenRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public OtpToken generateOtp(User user, OtpToken.OtpType type) {
        try {
            // Invalidate existing OTPs for this user and type
            invalidateUserOtps(user, type);

            // Generate new OTP
            String otp = generateRandomOtp();
            LocalDateTime expiry = LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES);

            // Create and save OTP token
            OtpToken otpToken = new OtpToken(otp, expiry, user, type);
            otpToken = otpTokenRepository.save(otpToken);

            logger.info("Generated OTP for user: {} with type: {}", user.getEmail(), type);
            return otpToken;
        } catch (Exception e) {
            logger.error("Error generating OTP for user: {}", user.getEmail(), e);
            throw new RuntimeException("Failed to generate OTP", e);
        }
    }

    @Override
    public boolean validateOtp(String email, String otp, OtpToken.OtpType type) {
        try {
            Optional<OtpToken> otpTokenOpt = otpTokenRepository
                .findByUser_EmailAndOtpAndTypeAndUsedFalse(email, otp, type);

            if (otpTokenOpt.isEmpty()) {
                logger.warn("Invalid OTP attempt for email: {} with type: {}", email, type);
                return false;
            }

            OtpToken otpToken = otpTokenOpt.get();
            
            if (!otpToken.isValid()) {
                logger.warn("Expired or used OTP attempt for email: {} with type: {}", email, type);
                return false;
            }

            // Mark OTP as used
            otpToken.setUsed(true);
            otpTokenRepository.save(otpToken);

            logger.info("Successfully validated OTP for email: {} with type: {}", email, type);
            return true;
        } catch (Exception e) {
            logger.error("Error validating OTP for email: {}", email, e);
            return false;
        }
    }

    @Override
    public boolean sendOtpEmail(User user, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Your Slotizen Verification Code");
            message.setText(buildOtpEmailContent(user.getFirstName(), otp));
            message.setFrom("noreply@slotizen.com");
            System.out.println("Sending email to: " + user.getEmail() + " with OTP: " + otp);

            //javaMailSender.send(message);
            
            logger.info("OTP email sent successfully to: {}", user.getEmail());
            return true;
        } catch (Exception e) {
            logger.error("Failed to send OTP email to: {}", user.getEmail(), e);
            return false;
        }
    }

    @Override
    public void cleanExpiredOtps() {
        try {
            int deletedCount = otpTokenRepository.deleteExpiredOtps(LocalDateTime.now());
            logger.info("Cleaned {} expired OTP tokens", deletedCount);
        } catch (Exception e) {
            logger.error("Error cleaning expired OTPs", e);
        }
    }

    @Override
    public void invalidateUserOtps(User user, OtpToken.OtpType type) {
        try {
            int invalidatedCount = otpTokenRepository.invalidateUserOtps(user, type);
            logger.debug("Invalidated {} OTP tokens for user: {} with type: {}", 
                invalidatedCount, user.getEmail(), type);
        } catch (Exception e) {
            logger.error("Error invalidating OTPs for user: {}", user.getEmail(), e);
        }
    }

    private String generateRandomOtp() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    private String buildOtpEmailContent(String firstName, String otp) {
        return String.format(
            "Dear %s,\n\n" +
            "Welcome to Slotizen! Please use the following verification code to complete your registration:\n\n" +
            "%s\n\n" +
            "This code will expire in %d minutes.\n\n" +
            "If you didn't request this verification code, please ignore this email.\n\n" +
            "Best regards,\n" +
            "The Slotizen Team",
            firstName, otp, OTP_EXPIRY_MINUTES
        );
    }
}