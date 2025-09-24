package com.slotizen.venus.service.impl;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.slotizen.venus.dto.BusinessHoursRequest;
import com.slotizen.venus.dto.BusinessHoursResponse;
import com.slotizen.venus.dto.BusinessProfileRequest;
import com.slotizen.venus.dto.BusinessProfileResponse;
import com.slotizen.venus.dto.LogoUploadResponse;
import com.slotizen.venus.model.BusinessHours;
import com.slotizen.venus.model.BusinessProfile;
import com.slotizen.venus.model.DailyHours;
import com.slotizen.venus.repository.BusinessHoursRepository;
import com.slotizen.venus.repository.BusinessProfileRepository;
import com.slotizen.venus.service.BusinessService;
import com.slotizen.venus.service.StorageService;

@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
    @Autowired private BusinessProfileRepository businessProfileRepository;
    @Autowired private BusinessHoursRepository businessHoursRepository;
    @Autowired
    private StorageService storageService;

    @Override
    public BusinessProfileResponse createOrUpdateProfile(BusinessProfileRequest request, UUID businessId) {
        BusinessProfile profile = (businessId != null) ?
            businessProfileRepository.findById(businessId).orElse(new BusinessProfile()) : new BusinessProfile();
        profile.setBusinessName(request.businessName);
        profile.setDescription(request.description);
        profile.setAddress(request.address);
        profile.setCity(request.city);
        profile.setState(request.state);
        profile.setZipCode(request.zipCode);
        profile.setPhone(request.phone);
        profile.setWebsite(request.website);
        profile.setTimezone(request.timezone);
        profile.setSlug(generateSlug(request.businessName));
        if (profile.getCreatedAt() == null) profile.setCreatedAt(ZonedDateTime.now());
        businessProfileRepository.save(profile);
        BusinessProfileResponse resp = new BusinessProfileResponse();
        resp.success = true;
        resp.message = (businessId == null) ? "Business profile created successfully" : "Business profile updated successfully";
        resp.data = new BusinessProfileResponse.Data();
        resp.data.businessId = profile.getBusinessId();
        resp.data.businessName = profile.getBusinessName();
        resp.data.slug = profile.getSlug();
        resp.data.createdAt = profile.getCreatedAt();
        return resp;
    }

    @Override
    public BusinessHoursResponse setupBusinessHours(UUID businessId, BusinessHoursRequest request) {
        BusinessProfile profile = businessProfileRepository.findById(businessId).orElse(null);
        if (profile == null) {
            BusinessHoursResponse resp = new BusinessHoursResponse();
            resp.success = false;
            resp.message = "Business not found";
            return resp;
        }
        BusinessHours hours = businessHoursRepository.findByBusinessProfile(profile);
        if (hours == null) hours = new BusinessHours();
        hours.setBusinessProfile(profile);

        // Mapping
        var bh = request.businessHours;
        hours.setMonday(toDaily(bh.monday));
        hours.setTuesday(toDaily(bh.tuesday));
        hours.setWednesday(toDaily(bh.wednesday));
        hours.setThursday(toDaily(bh.thursday));
        hours.setFriday(toDaily(bh.friday));
        hours.setSaturday(toDaily(bh.saturday));
        hours.setSunday(toDaily(bh.sunday));

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
    public LogoUploadResponse uploadLogo(Long userId, String businessId, MultipartFile file) {
        BusinessProfile profile = businessProfileRepository.findById(UUID.fromString(businessId))
                .orElseThrow(() -> new IllegalArgumentException("Business not found"));
        // (Optional) verify userId owns this business
        String url = storageService.store("business-logo", businessId, file);
        profile.setLogoUrl(url);
        businessProfileRepository.save(profile);
        return new LogoUploadResponse(true, "Logo uploaded", url);
    }
    private DailyHours toDaily(BusinessHoursRequest.Day d) {
        if (d == null) return null;
        return new DailyHours(
            d.isOpen,
            parseTime(d.openTime),
            parseTime(d.closeTime)
        );
    }

    private LocalTime parseTime(String v) {
        return (v == null || v.isBlank()) ? null : LocalTime.parse(v);
    }

    private String generateSlug(String name) {
        return name == null ? null : name.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
    }
}
