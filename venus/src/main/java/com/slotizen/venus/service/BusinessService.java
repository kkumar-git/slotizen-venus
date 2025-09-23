package com.slotizen.venus.service;

import com.slotizen.venus.dto.BusinessProfileRequest;
import com.slotizen.venus.dto.BusinessProfileResponse;
import com.slotizen.venus.dto.BusinessHoursRequest;
import com.slotizen.venus.dto.BusinessHoursResponse;
import java.util.UUID;

public interface BusinessService {
    BusinessProfileResponse createOrUpdateProfile(BusinessProfileRequest request, UUID businessId);
    BusinessHoursResponse setupBusinessHours(UUID businessId, BusinessHoursRequest request);
}
