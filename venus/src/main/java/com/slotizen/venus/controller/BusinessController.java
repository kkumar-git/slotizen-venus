package com.slotizen.venus.controller;

import com.slotizen.venus.dto.*;
import com.slotizen.venus.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/business")
public class BusinessController {
    @Autowired private BusinessService businessService;

    @PostMapping("/profile")
    public ResponseEntity<BusinessProfileResponse> createProfile(@RequestBody BusinessProfileRequest request) {
        return ResponseEntity.ok(businessService.createOrUpdateProfile(request, null));
    }

    @PutMapping("/profile/{businessId}")
    public ResponseEntity<BusinessProfileResponse> updateProfile(@PathVariable UUID businessId, @RequestBody BusinessProfileRequest request) {
        return ResponseEntity.ok(businessService.createOrUpdateProfile(request, businessId));
    }

    @PostMapping("/{businessId}/hours")
    public ResponseEntity<BusinessHoursResponse> setupHours(@PathVariable UUID businessId, @RequestBody BusinessHoursRequest request) {
        return ResponseEntity.ok(businessService.setupBusinessHours(businessId, request));
    }
}
