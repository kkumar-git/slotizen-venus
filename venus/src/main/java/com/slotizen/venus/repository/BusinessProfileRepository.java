package com.slotizen.venus.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.slotizen.venus.model.BusinessProfile;

public interface BusinessProfileRepository extends JpaRepository<BusinessProfile, Long> {
    boolean existsBySlug(String slug);
    @Query(
    		  value = "SELECT bp.* FROM business_profile bp " +
    		          "JOIN user_business ub ON bp.business_id = ub.business_id " +
    		          "WHERE ub.user_id = :userId LIMIT 1",
    		  nativeQuery = true
    		)
    BusinessProfile findByUserId(@Param("userId") Long userId);
}
