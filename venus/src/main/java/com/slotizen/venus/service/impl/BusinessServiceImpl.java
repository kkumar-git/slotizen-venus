package com.slotizen.venus.service.impl;

import com.slotizen.venus.dto.*;
import com.slotizen.venus.model.*;
import com.slotizen.venus.repository.*;
import com.slotizen.venus.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
    @Autowired private BusinessProfileRepository businessProfileRepository;
    @Autowired private BusinessHoursRepository businessHoursRepository;

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
        // Map request.businessHours to hours' fields (monday, tuesday, ...)
        // ... (mapping logic to be implemented)
        businessHoursRepository.save(hours);
        BusinessHoursResponse resp = new BusinessHoursResponse();
        resp.success = true;
        resp.message = "Business hours updated successfully";
        resp.data = new BusinessHoursResponse.Data();
        resp.data.businessId = businessId;
        resp.data.businessHours = request.businessHours;
        return resp;
    }

    private String generateSlug(String name) {
        return name == null ? null : name.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
    }
}
