package com.slotizen.venus.repository;

import com.slotizen.venus.model.Client;
import com.slotizen.venus.model.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    // Find all clients for a specific business
    List<Client> findByBusinessIdOrderByCreatedAtDesc(Long businessId);
    
    // Find by business and status
    List<Client> findByBusinessIdAndStatus(Long businessId, ClientStatus status);
    
    // Find by business and ID
    Optional<Client> findByBusinessIdAndId(Long businessId, Long id);
    
    // Check if email exists for this business (for duplicate prevention)
    boolean existsByBusinessIdAndEmail(Long businessId, String email);
    
    // Check if email exists for another client (for updates)
    boolean existsByBusinessIdAndEmailAndIdNot(Long businessId, String email, Long id);
    
    // Search clients by name or email
    @Query("SELECT c FROM Client c WHERE c.businessId = :businessId " +
           "AND (LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(c.email) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<Client> searchClients(@Param("businessId") Long businessId, 
                               @Param("search") String search);
    
    // Count clients for dashboard
    long countByBusinessId(Long businessId);
    
    // Count active clients
    long countByBusinessIdAndStatus(Long businessId, ClientStatus status);
    
    // Find by email (legacy method for backward compatibility)
    Optional<Client> findByEmail(String email);
    
    // Check if email exists (legacy method for backward compatibility)
    boolean existsByEmail(String email);
}