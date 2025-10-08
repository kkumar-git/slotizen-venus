package com.slotizen.venus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.slotizen.venus.model.ServiceEntity;
import com.slotizen.venus.model.ServiceStatus;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    
    // Find all services for a business
    List<ServiceEntity> findByBusinessIdOrderByCreatedAtDesc(Long businessId);
    
    // Find services by business and status
    List<ServiceEntity> findByBusinessIdAndStatus(Long businessId, ServiceStatus status);
    
    // Find service by business and ID (for security)
    Optional<ServiceEntity> findByBusinessIdAndId(Long businessId, Long id);
    
    // Delete all services for a business
    void deleteByBusinessId(Long businessId);
    
    // Count services for a business
    long countByBusinessId(Long businessId);
    
    // Count active services for a business
    long countByBusinessIdAndStatus(Long businessId, ServiceStatus status);
    
    // Search services by name or category
    @Query("SELECT s FROM ServiceEntity s WHERE s.businessId = :businessId " +
           "AND (LOWER(s.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(s.category) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<ServiceEntity> searchServices(@Param("businessId") Long businessId, 
                                       @Param("search") String search);
}