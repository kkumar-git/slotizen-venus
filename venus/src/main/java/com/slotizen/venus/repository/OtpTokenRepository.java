package com.slotizen.venus.repository;

import com.slotizen.venus.model.OtpToken;
import com.slotizen.venus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OtpTokenRepository extends JpaRepository<OtpToken, Long> {
    Optional<OtpToken> findByUserAndOtpAndTypeAndUsedFalse(User user, String otp, OtpToken.OtpType type);
    
    Optional<OtpToken> findByUser_EmailAndOtpAndTypeAndUsedFalse(String email, String otp, OtpToken.OtpType type);
    
    List<OtpToken> findByUserAndTypeAndUsedFalse(User user, OtpToken.OtpType type);
    
    @Modifying
    @Transactional
    @Query("UPDATE OtpToken o SET o.used = true WHERE o.user = :user AND o.type = :type AND o.used = false")
    int invalidateUserOtps(@Param("user") User user, @Param("type") OtpToken.OtpType type);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM OtpToken o WHERE o.expiry < :now")
    int deleteExpiredOtps(LocalDateTime now);
}
