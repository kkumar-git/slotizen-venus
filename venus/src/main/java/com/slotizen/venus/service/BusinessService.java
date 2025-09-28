package com.slotizen.venus.service;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.slotizen.venus.dto.BusinessHoursRequest;
import com.slotizen.venus.dto.BusinessHoursResponse;
import com.slotizen.venus.dto.BusinessProfileRequest;
import com.slotizen.venus.dto.BusinessProfileResponse;
import com.slotizen.venus.dto.LogoUploadResponse;

public interface BusinessService {
	BusinessProfileResponse getBusinessProfile();
    BusinessProfileResponse createOrUpdateProfile(BusinessProfileRequest request, UUID businessId);
    BusinessHoursResponse setupBusinessHours(UUID businessId, BusinessHoursRequest request);
    LogoUploadResponse uploadLogo(Long userId, String businessId, MultipartFile file);
    LogoUploadResponse uploadImage(Long userId, MultipartFile file);
    boolean deleteImage(Long userId);
}
