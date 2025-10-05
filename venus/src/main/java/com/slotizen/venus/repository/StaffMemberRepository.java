package com.slotizen.venus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.slotizen.venus.model.StaffMember;
import com.slotizen.venus.model.StaffStatus;

@Repository
public interface StaffMemberRepository extends JpaRepository<StaffMember, Long> {
    
    List<StaffMember> findByBusinessIdOrderByCreatedAtDesc(Long businessId);

    List<StaffMember> findByBusinessIdAndStatusOrderByCreatedAtDesc(Long businessId, StaffStatus status);

    Optional<StaffMember> findByIdAndBusinessId(Long id, Long businessId);

    boolean existsByEmailAndBusinessId(String email, Long businessId);

    boolean existsByEmailAndBusinessIdAndIdNot(String email, Long businessId, Long id);

    @Query("SELECT s FROM StaffMember s WHERE s.businessId = :businessId AND :serviceId MEMBER OF s.services")
    List<StaffMember> findStaffByBusinessIdAndServiceId(@Param("businessId") Long businessId, @Param("serviceId") Long serviceId);

    @Query("SELECT COUNT(s) FROM StaffMember s WHERE s.businessId = :businessId AND s.status = :status")
    long countByBusinessIdAndStatus(@Param("businessId") Long businessId, @Param("status") StaffStatus status);

    void deleteByIdAndBusinessId(Long id, Long businessId);
}