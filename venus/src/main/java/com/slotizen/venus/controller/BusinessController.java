package com.slotizen.venus.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.slotizen.venus.dto.BusinessHoursRequest;
import com.slotizen.venus.dto.BusinessHoursResponse;
import com.slotizen.venus.dto.BusinessProfileRequest;
import com.slotizen.venus.dto.BusinessProfileResponse;
import com.slotizen.venus.dto.LogoUploadResponse;
import com.slotizen.venus.service.BusinessService;
import com.slotizen.venus.util.SecurityUtils;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @PostMapping("/profile")
    public ResponseEntity<BusinessProfileResponse> createProfile(@RequestBody BusinessProfileRequest request) {
        return ResponseEntity.ok(businessService.createOrUpdateProfile(request, null));
    }

    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public LogoUploadResponse uploadImage(@RequestPart("file") MultipartFile file, Authentication authentication) {
        Long userId = extractUserId(authentication);
        return businessService.uploadImage(userId, file);
    }

    @PostMapping(value = "/delete-image")
    public ResponseEntity<String> deleteImage(Authentication authentication) {
        Long userId = extractUserId(authentication);
        boolean deleted = businessService.deleteImage(userId);
        if (deleted) {
            return ResponseEntity.ok("Image deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Image not found or could not be deleted");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<BusinessProfileResponse> geProfile() {
        return ResponseEntity.ok(businessService.getBusinessProfile());
    }

    @PutMapping("/profile/{businessId}")
    public ResponseEntity<BusinessProfileResponse> updateProfile(@PathVariable UUID businessId,
            @RequestBody BusinessProfileRequest request) {
        return ResponseEntity.ok(businessService.createOrUpdateProfile(request, businessId));
    }

    @PostMapping("/{businessId}/hours")
    public ResponseEntity<BusinessHoursResponse> setupHours(@PathVariable UUID businessId,
            @RequestBody BusinessHoursRequest request) {
        return ResponseEntity.ok(businessService.setupBusinessHours(businessId, request));
    }

    @PostMapping(value = "/{businessId}/logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public LogoUploadResponse uploadLogo(
            @PathVariable String businessId,
            @RequestPart("file") MultipartFile file,
            Authentication authentication) {
        // Extract userId from auth principal or token (adjust to your security context)
        Long userId = extractUserId(authentication);
        return businessService.uploadLogo(userId, businessId, file);
    }

    private Long extractUserId(Authentication auth) {
        return SecurityUtils.getCurrentUserId();
    }
}
