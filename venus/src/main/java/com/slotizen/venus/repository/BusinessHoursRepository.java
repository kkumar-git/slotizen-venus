package com.slotizen.venus.repository;

import com.slotizen.venus.model.BusinessHours;
import com.slotizen.venus.model.BusinessProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, UUID> {
    BusinessHours findByBusinessProfile(BusinessProfile businessProfile);
}
