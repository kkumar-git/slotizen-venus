package com.slotizen.venus.repository;

import com.slotizen.venus.model.UserBusiness;
import com.slotizen.venus.dto.UserBusinessDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserBusinessRepository extends JpaRepository<UserBusiness, UserBusiness.UserBusinessId> {
    
    /**
     * Find all user-business relationships for a specific user
     */
    List<UserBusiness> findByUserId(Long userId);
    
    /**
     * Find all user-business relationships for a specific business
     */
    List<UserBusiness> findByBusinessId(UUID businessId);
    
    /**
     * Check if a user is associated with a business
     */
    boolean existsByUserIdAndBusinessId(Long userId, UUID businessId);
    
    /**
     * Delete user-business relationship
     */
    void deleteByUserIdAndBusinessId(Long userId, UUID businessId);
    
    /**
     * Count total businesses for a user
     */
    @Query("SELECT COUNT(ub) FROM UserBusiness ub WHERE ub.userId = :userId")
    long countBusinessesByUserId(@Param("userId") Long userId);
    
    /**
     * Count total users for a business
     */
    @Query("SELECT COUNT(ub) FROM UserBusiness ub WHERE ub.businessId = :businessId")
    long countUsersByBusinessId(@Param("businessId") UUID businessId);
    
    /**
     * Find user-business relationships with business name in single query
     */
    @Query("SELECT new com.slotizen.venus.dto.UserBusinessDto(ub.userId, ub.businessId, bp.businessName, bp.logoUrl, bp.businessType) " +
           "FROM UserBusiness ub " +
           "JOIN BusinessProfile bp ON bp.businessId = ub.businessId " +
           "WHERE ub.userId = :userId")
    List<UserBusinessDto> findUserBusinessesWithNameByUserId(@Param("userId") Long userId);
}