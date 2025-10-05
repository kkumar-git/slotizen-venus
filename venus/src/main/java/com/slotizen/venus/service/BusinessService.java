package com.slotizen.venus.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.slotizen.venus.dto.BusinessHoursRequest;
import com.slotizen.venus.dto.BusinessHoursResponse;
import com.slotizen.venus.dto.BusinessProfileRequest;
import com.slotizen.venus.dto.BusinessProfileResponse;
import com.slotizen.venus.dto.LogoUploadResponse;
import com.slotizen.venus.dto.ServiceDto;
import com.slotizen.venus.dto.SingleStaffRequest;
import com.slotizen.venus.dto.StaffDto;
import com.slotizen.venus.model.ServiceEntity;
import com.slotizen.venus.model.StaffMember;

public interface BusinessService {
	BusinessProfileResponse getBusinessProfile();
    BusinessProfileResponse createOrUpdateProfile(BusinessProfileRequest request, Long businessId);
    BusinessHoursResponse setupBusinessHours(Long businessId, BusinessHoursRequest request);
    LogoUploadResponse uploadLogo(Long userId, Long businessId, MultipartFile file);
    LogoUploadResponse uploadImage(Long userId, MultipartFile file);
    boolean deleteImage(Long userId);
    List<ServiceEntity> saveServices(Long businessId, List<ServiceDto> serviceDtos);

    // Staff Management Methods
    StaffDto createStaffMember(Long businessId, SingleStaffRequest request);
    List<StaffDto> getAllStaff(Long businessId);
    StaffDto getStaffById(Long businessId, Long staffId);
    StaffDto updateStaffMember(Long businessId, Long staffId, SingleStaffRequest request);
    boolean deleteStaffMember(Long businessId, Long staffId);
    List<StaffDto> getStaffByService(Long businessId, Long serviceId);

    // Helper method to update business profile completion status
    void updateBusinessProfileCompletion(Long businessId);
}
