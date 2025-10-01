package com.slotizen.venus.service;

import java.util.List;
import java.util.UUID;

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
    BusinessProfileResponse createOrUpdateProfile(BusinessProfileRequest request, UUID businessId);
    BusinessHoursResponse setupBusinessHours(UUID businessId, BusinessHoursRequest request);
    LogoUploadResponse uploadLogo(Long userId, String businessId, MultipartFile file);
    LogoUploadResponse uploadImage(Long userId, MultipartFile file);
    boolean deleteImage(Long userId);
    List<ServiceEntity> saveServices(String businessId, List<ServiceDto> serviceDtos);
    
    // Staff Management Methods
    StaffDto createStaffMember(String businessId, SingleStaffRequest request);
    List<StaffDto> getAllStaff(String businessId);
    StaffDto getStaffById(String businessId, Long staffId);
    StaffDto updateStaffMember(String businessId, Long staffId, SingleStaffRequest request);
    boolean deleteStaffMember(String businessId, Long staffId);
    List<StaffDto> getStaffByService(String businessId, String serviceId);
    
    // Helper method to update business profile completion status
    void updateBusinessProfileCompletion(String businessId);
}
