package com.slotizen.venus.repository;

import com.slotizen.venus.model.BusinessHours;
import com.slotizen.venus.model.BusinessProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {
    BusinessHours findByBusinessProfile(BusinessProfile businessProfile);
}
