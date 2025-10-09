package com.slotizen.venus.service.impl;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.slotizen.venus.dto.BusinessHoursRequest;
import com.slotizen.venus.dto.BusinessHoursResponse;
import com.slotizen.venus.dto.BusinessProfileRequest;
import com.slotizen.venus.dto.BusinessProfileResponse;
import com.slotizen.venus.dto.LogoUploadResponse;
import com.slotizen.venus.dto.ServiceDto;
import com.slotizen.venus.dto.SingleStaffRequest;
import com.slotizen.venus.dto.StaffDto;
import com.slotizen.venus.model.BusinessHours;
import com.slotizen.venus.model.BusinessProfile;
import com.slotizen.venus.model.DailyHours;
import com.slotizen.venus.model.Department;
import com.slotizen.venus.model.ServiceEntity;
import com.slotizen.venus.model.StaffMember;
import com.slotizen.venus.model.UserBusiness;
import com.slotizen.venus.repository.BusinessHoursRepository;
import com.slotizen.venus.repository.BusinessProfileRepository;
import com.slotizen.venus.repository.DepartmentRepository;
import com.slotizen.venus.repository.ServiceRepository;
import com.slotizen.venus.repository.StaffMemberRepository;
import com.slotizen.venus.repository.UserBusinessRepository;
import com.slotizen.venus.service.BusinessService;
import com.slotizen.venus.service.StorageService;
import com.slotizen.venus.util.SecurityUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessProfileRepository businessProfileRepository;
    @Autowired
    private BusinessHoursRepository businessHoursRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private StaffMemberRepository staffMemberRepository;
    @Autowired
    private UserBusinessRepository userBusinessRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private StorageService storageService;

    @Override
    public BusinessProfileResponse createOrUpdateProfile(BusinessProfileRequest request, Long businessId) {
        BusinessProfile profile = (businessId != null)
                ? businessProfileRepository.findById(businessId).orElse(new BusinessProfile())
                : new BusinessProfile();
        profile.setLogoUrl(request.logoUrl);
        profile.setBusinessName(request.businessName);
        profile.setBusinessType(request.businessType);
        profile.setDescription(request.description);
        profile.setAddress(request.address);
        profile.setCity(request.city);
        profile.setState(request.state);
        profile.setZipCode(request.zipCode);
        profile.setPhone(request.phone);
        profile.setWebsite(request.website);
        profile.setTimezone(request.timezone);
        profile.setDefaultDuration(request.defaultDuration != null ? request.defaultDuration : 60);
        profile.setCurrency(request.currency != null ? request.currency : "USD");
        profile.setSlug(generateSlug(request.businessName));
        profile.setCompetitionLevel(1);
        if (profile.getCreatedAt() == null)
            profile.setCreatedAt(ZonedDateTime.now());

        // Save the business profile
        businessProfileRepository.save(profile);

        // Create user_business mapping for new profiles
        if (businessId == null) {
            Long currentUserId = SecurityUtils.getCurrentUserId();
            if (currentUserId != null) {
                // Check if mapping already exists to avoid duplicates
                if (!userBusinessRepository.existsByUserIdAndBusinessId(currentUserId, profile.getBusinessId())) {
                    UserBusiness userBusiness = new UserBusiness(currentUserId, profile.getBusinessId());
                    userBusinessRepository.save(userBusiness);
                }
            }
        }

        BusinessProfileResponse resp = new BusinessProfileResponse();
        resp.businessId = profile.getBusinessId();
        resp.success = true;
        resp.message = (businessId == null) ? "Business profile created successfully"
                : "Business profile updated successfully";
        resp.data = new BusinessProfileResponse.Data();
        resp.data.businessId = profile.getBusinessId();
        resp.data.businessName = profile.getBusinessName();
        resp.data.slug = profile.getSlug();
        resp.data.createdAt = profile.getCreatedAt();
        resp.data.defaultDuration = profile.getDefaultDuration();
        resp.data.currency = profile.getCurrency();
        return resp;
    }

    public BusinessProfileResponse getBusinessProfile() {
        Long userId = SecurityUtils.getCurrentUserId();
        BusinessProfileResponse resp = new BusinessProfileResponse();
        BusinessProfile profile = businessProfileRepository.findByUserId(userId);
        if (profile == null) {
            return resp;
        } else {

            resp.success = true;
            resp.message = "Business profile fetched successfully";
            resp.data = new BusinessProfileResponse.Data();
            resp.data.businessId = profile.getBusinessId();
            resp.data.businessName = profile.getBusinessName();
            resp.data.logoUrl = profile.getLogoUrl();
            resp.data.businessType = profile.getBusinessType();
            resp.data.description = profile.getDescription();
            resp.data.address = profile.getAddress();
            resp.data.city = profile.getCity();
            resp.data.state = profile.getState();
            resp.data.zipCode = profile.getZipCode();
            resp.data.phone = profile.getPhone();
            resp.data.website = profile.getWebsite();
            resp.data.timezone = profile.getTimezone();
            resp.data.active = profile.isActive();
            resp.data.competitionLevel = profile.getCompetitionLevel();
            resp.data.completed = profile.isCompleted();
            resp.data.defaultDuration = profile.getDefaultDuration();
            resp.data.currency = profile.getCurrency();
            resp.data.slug = profile.getSlug();
            resp.data.createdAt = profile.getCreatedAt();
            return resp;
        }

    }

    @Override
    public BusinessHoursResponse setupBusinessHours(Long businessId, BusinessHoursRequest request) {
        BusinessProfile profile = businessProfileRepository.findById(businessId).orElse(null);
        if (profile == null) {
            BusinessHoursResponse resp = new BusinessHoursResponse();
            resp.success = false;
            resp.message = "Business not found";
            return resp;
        }
        String businessTimezone = profile.getTimezone();
        ZoneId businessZone = businessTimezone != null ? ZoneId.of(businessTimezone) : ZoneId.systemDefault();

        BusinessHours hours = businessHoursRepository.findByBusinessProfile(profile);
        if (hours == null)
            hours = new BusinessHours();
        hours.setBusinessProfile(profile);

        // Mapping
        var bh = request.businessHours;
        hours.setMonday(toDailyWithTimezone(bh.monday, businessZone));
        hours.setTuesday(toDailyWithTimezone(bh.tuesday, businessZone));
        hours.setWednesday(toDailyWithTimezone(bh.wednesday, businessZone));
        hours.setThursday(toDailyWithTimezone(bh.thursday, businessZone));
        hours.setFriday(toDailyWithTimezone(bh.friday, businessZone));
        hours.setSaturday(toDailyWithTimezone(bh.saturday, businessZone));
        hours.setSunday(toDailyWithTimezone(bh.sunday, businessZone));

        businessHoursRepository.save(hours);

        BusinessHoursResponse resp = new BusinessHoursResponse();
        resp.success = true;
        resp.message = "Business hours updated successfully";
        resp.data = new BusinessHoursResponse.Data();
        resp.data.businessId = businessId;
        resp.data.businessHours = request.businessHours;
        return resp;
    }

    @Override
    public BusinessHoursResponse getBusinessHours(Long businessId) {
        BusinessProfile profile = businessProfileRepository.findById(businessId).orElse(null);
        if (profile == null) {
            BusinessHoursResponse resp = new BusinessHoursResponse();
            resp.success = false;
            resp.message = "Business not found";
            return resp;
        }

        BusinessHours hours = businessHoursRepository.findByBusinessProfile(profile);
        BusinessHoursResponse resp = new BusinessHoursResponse();
        resp.success = true;
        resp.message = "Business hours retrieved successfully";
        resp.data = new BusinessHoursResponse.Data();
        resp.data.businessId = businessId;
        
        if (hours != null) {
            // Convert back to response format
            String businessTimezone = profile.getTimezone();
            ZoneId businessZone = businessTimezone != null ? ZoneId.of(businessTimezone) : ZoneId.systemDefault();

            BusinessHoursRequest.Hours businessHours = new BusinessHoursRequest.Hours();
            businessHours.monday = fromDailyWithTimezone(hours.getMonday(), businessZone);
            businessHours.tuesday = fromDailyWithTimezone(hours.getTuesday(), businessZone);
            businessHours.wednesday = fromDailyWithTimezone(hours.getWednesday(), businessZone);
            businessHours.thursday = fromDailyWithTimezone(hours.getThursday(), businessZone);
            businessHours.friday = fromDailyWithTimezone(hours.getFriday(), businessZone);
            businessHours.saturday = fromDailyWithTimezone(hours.getSaturday(), businessZone);
            businessHours.sunday = fromDailyWithTimezone(hours.getSunday(), businessZone);
            
            resp.data.businessHours = businessHours;
        } else {
            // Return default empty business hours if none exist
            resp.data.businessHours = new BusinessHoursRequest.Hours();
        }
        
        return resp;
    }

    @Override
    public LogoUploadResponse uploadImage(Long userId, MultipartFile file) {
        // Store the image, use userId as folder or key
        String url = storageService.store("user-image", String.valueOf(userId), file);
        return new LogoUploadResponse(true, "Image uploaded", url);
    }

    @Override
    public LogoUploadResponse uploadLogo(Long userId, Long businessId, MultipartFile file) {
        BusinessProfile profile = businessProfileRepository.findById(businessId)
                .orElseThrow(() -> new IllegalArgumentException("Business not found"));
        // (Optional) verify userId owns this business
        String url = storageService.store("business-logo", businessId.toString(), file);
        profile.setLogoUrl(url);
        businessProfileRepository.save(profile);
        return new LogoUploadResponse(true, "Logo uploaded", url);
    }

    @Override
    public boolean deleteImage(Long userId) {
        // Delete the image for the user
        return storageService.delete("user-image", String.valueOf(userId));
    }

    private DailyHours toDaily(BusinessHoursRequest.Day d) {
        if (d == null)
            return null;
        return new DailyHours(
                d.isOpen,
                d.openTime,
                d.closeTime);
    }

    private LocalTime parseTime(String v) {
        return (v == null || v.isBlank()) ? null : LocalTime.parse(v);
    }

    @Override
    public List<ServiceEntity> saveServices(Long businessId, List<ServiceDto> serviceDtos) {
        // Clear existing services for this business (replace all)
        // UUID businessUUID = UUID.fromString(businessId);
        serviceRepository.deleteByBusinessId(businessId);

        // Convert DTOs to entities and save
        List<ServiceEntity> services = serviceDtos.stream()
                .map(dto -> convertToEntity(dto, businessId))
                .collect(Collectors.toList());

        return serviceRepository.saveAll(services);
    }

    private ServiceEntity convertToEntity(ServiceDto dto, Long businessId) {
        ServiceEntity service = new ServiceEntity();
        service.setName(dto.getName().trim());
        service.setDescription(dto.getDescription().trim());
        service.setDuration(dto.getDuration());
        service.setPrice(dto.getPrice());
        service.setCategory(dto.getCategory());
        service.setBusinessId(businessId);
        service.setDepartmentId(dto.getDepartmentId());
        return service;
    }

    private String generateSlug(String name) {
        return name == null ? null : name.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
    }

    // Staff Management Implementation

    @Override
    public StaffDto createStaffMember(Long businessId, SingleStaffRequest request) {
        // Check if email already exists for this business
        if (staffMemberRepository.existsByEmailAndBusinessId(request.getEmail(), businessId)) {
            throw new IllegalArgumentException("Email already exists for another staff member");
        }

        StaffMember staffMember = new StaffMember(
                request.getFirstName().trim(),
                request.getLastName().trim(),
                request.getEmail().trim().toLowerCase(),
                request.getPhone().trim(),
                request.getRole().trim(),
                businessId);

        if (request.getServices() != null && !request.getServices().isEmpty()) {
            staffMember.setServices(request.getServices());
        }

        if (request.getDepartmentId() != null) {
            staffMember.setDepartmentId(request.getDepartmentId());
        }

        StaffMember saved = staffMemberRepository.save(staffMember);

        // Update business profile completion status after successfully saving staff
        updateBusinessProfileCompletion(businessId);

        return convertToStaffDto(saved);
    }

    @Override
    public List<StaffDto> getAllStaff(Long businessId) {
        List<StaffMember> staffMembers = staffMemberRepository.findByBusinessIdOrderByCreatedAtDesc(businessId);
        return staffMembers.stream()
                .map(this::convertToStaffDto)
                .collect(Collectors.toList());
    }

    @Override
    public StaffDto getStaffById(Long businessId, Long staffId) {
        StaffMember staffMember = staffMemberRepository.findByIdAndBusinessId(staffId, businessId)
                .orElseThrow(() -> new IllegalArgumentException("Staff member not found"));
        return convertToStaffDto(staffMember);
    }

    @Override
    public StaffDto updateStaffMember(Long businessId, Long staffId, SingleStaffRequest request) {
        StaffMember staffMember = staffMemberRepository.findByIdAndBusinessId(staffId, businessId)
                .orElseThrow(() -> new IllegalArgumentException("Staff member not found"));

        // Check if email already exists for another staff member
        if (staffMemberRepository.existsByEmailAndBusinessIdAndIdNot(request.getEmail(), businessId, staffId)) {
            log.error("Attempt to update staff member with duplicate email: {}", request.getEmail());
            //throw new IllegalArgumentException("Email already exists for another staff member");
        }

        staffMember.setFirstName(request.getFirstName().trim());
        staffMember.setLastName(request.getLastName().trim());
        staffMember.setEmail(request.getEmail().trim().toLowerCase());
        staffMember.setPhone(request.getPhone().trim());
        staffMember.setRole(request.getRole().trim());

        if (request.getServices() != null) {
            staffMember.setServices(request.getServices());
        }

        if (request.getDepartmentId() != null) {
            staffMember.setDepartmentId(request.getDepartmentId());
        }

        StaffMember updated = staffMemberRepository.save(staffMember);
        return convertToStaffDto(updated);
    }

    @Override
    public boolean deleteStaffMember(Long businessId, Long staffId) {
        StaffMember staffMember = staffMemberRepository.findByIdAndBusinessId(staffId, businessId)
                .orElseThrow(() -> new IllegalArgumentException("Staff member not found"));

        staffMemberRepository.delete(staffMember);
        return true;
    }

    @Override
    public List<StaffDto> getStaffByService(Long businessId, Long serviceId) {
        List<StaffMember> staffMembers = staffMemberRepository.findStaffByBusinessIdAndServiceId(businessId, serviceId);
        return staffMembers.stream()
                .map(this::convertToStaffDto)
                .collect(Collectors.toList());
    }

    private StaffDto convertToStaffDto(StaffMember staffMember) {
        StaffDto dto = new StaffDto(
                staffMember.getId(),
                staffMember.getFirstName(),
                staffMember.getLastName(),
                staffMember.getEmail(),
                staffMember.getPhone(),
                staffMember.getRole(),
                staffMember.getStatus(),
                staffMember.getHireDate(),
                staffMember.getAvatar(),
                staffMember.getServices(),
                staffMember.getCreatedAt(),
                staffMember.getUpdatedAt());
        dto.setDepartmentId(staffMember.getDepartmentId());
        
        // Set department name if departmentId exists
        if (staffMember.getDepartmentId() != null) {
            departmentRepository.findById(staffMember.getDepartmentId())
                    .ifPresent(department -> dto.setDepartmentName(department.getName()));
        }
        
        return dto;
    }

    @Override
    public void updateBusinessProfileCompletion(Long businessId) {
        try {
            // Find the business profile by businessId
            java.util.Optional<BusinessProfile> profileOpt = businessProfileRepository
                    .findById(businessId);

            if (profileOpt.isPresent()) {
                BusinessProfile profile = profileOpt.get();
                // UUID businessUUID = profile.getBusinessId();
                // Check if business setup is complete
                boolean hasServices = !serviceRepository.findByBusinessIdOrderByCreatedAtDesc(businessId).isEmpty();
                boolean hasStaff = !staffMemberRepository.findByBusinessIdOrderByCreatedAtDesc(businessId).isEmpty();
                boolean hasBasicInfo = profile.getBusinessName() != null &&
                        profile.getBusinessType() != null &&
                        profile.getDescription() != null;

                // Set completed flag to true if all requirements are met
                if (hasBasicInfo && hasServices && hasStaff) {
                    profile.setCompleted(true);
                    businessProfileRepository.save(profile);
                }
            }
        } catch (Exception e) {
            // Log error but don't fail the staff creation
            System.err.println("Failed to update business profile completion status: " + e.getMessage());
        }
    }

    private DailyHours toDailyWithTimezone(BusinessHoursRequest.Day d, ZoneId businessZone) {
        if (d == null)
            return null;

        // Truncate seconds and handle timezone
        String openTime = null;
        String closeTime = null;

        if (d.openTime != null) {
            openTime = d.openTime;
        }

        if (d.closeTime != null) {
            closeTime = d.closeTime;
        }

        return new DailyHours(
                d.isOpen,
                openTime,
                closeTime);
    }

    private BusinessHoursRequest.Day fromDailyWithTimezone(DailyHours dailyHours, ZoneId businessZone) {
        if (dailyHours == null) {
            BusinessHoursRequest.Day day = new BusinessHoursRequest.Day();
            day.isOpen = false;
            return day;
        }

        BusinessHoursRequest.Day day = new BusinessHoursRequest.Day();
        day.isOpen = dailyHours.getIsOpen();
        day.openTime = dailyHours.getOpenTime();
        day.closeTime = dailyHours.getCloseTime();
        
        return day;
    }
}
