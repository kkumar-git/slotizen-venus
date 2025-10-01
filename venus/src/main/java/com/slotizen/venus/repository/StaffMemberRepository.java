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
    
    List<StaffMember> findByBusinessIdOrderByCreatedAtDesc(String businessId);
    
    List<StaffMember> findByBusinessIdAndStatusOrderByCreatedAtDesc(String businessId, StaffStatus status);
    
    Optional<StaffMember> findByIdAndBusinessId(Long id, String businessId);
    
    boolean existsByEmailAndBusinessId(String email, String businessId);
    
    boolean existsByEmailAndBusinessIdAndIdNot(String email, String businessId, Long id);
    
    @Query("SELECT s FROM StaffMember s WHERE s.businessId = :businessId AND :serviceId MEMBER OF s.services")
    List<StaffMember> findStaffByBusinessIdAndServiceId(@Param("businessId") String businessId, @Param("serviceId") String serviceId);
    
    @Query("SELECT COUNT(s) FROM StaffMember s WHERE s.businessId = :businessId AND s.status = :status")
    long countByBusinessIdAndStatus(@Param("businessId") String businessId, @Param("status") StaffStatus status);
    
    void deleteByIdAndBusinessId(Long id, String businessId);
}