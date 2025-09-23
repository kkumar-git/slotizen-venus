package com.slotizen.venus.repository;

import com.slotizen.venus.model.BusinessProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BusinessProfileRepository extends JpaRepository<BusinessProfile, UUID> {
    boolean existsBySlug(String slug);
}
