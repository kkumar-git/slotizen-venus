package com.slotizen.venus.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.slotizen.venus.model.DemoRequest;
import com.slotizen.venus.model.DemoRequestStatus;

@Repository
public interface DemoRequestRepository extends JpaRepository<DemoRequest, Long> {
    
    Optional<DemoRequest> findByEmailAndCreatedAtAfter(String email, LocalDateTime createdAt);
    
    List<DemoRequest> findByStatusOrderByCreatedAtDesc(DemoRequestStatus status);
    
    @Query("SELECT d FROM DemoRequest d WHERE d.createdAt >= :startDate AND d.createdAt <= :endDate")
    List<DemoRequest> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                    @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(d) FROM DemoRequest d WHERE d.email = :email AND d.createdAt >= :yesterday")
    long countByEmailAndCreatedAtAfter(@Param("email") String email, @Param("yesterday") LocalDateTime yesterday);
}