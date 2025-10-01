package com.slotizen.venus.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slotizen.venus.dto.DemoRequestDto;
import com.slotizen.venus.dto.DemoRequestResponse;
import com.slotizen.venus.exception.DuplicateRequestException;
import com.slotizen.venus.model.DemoRequest;
import com.slotizen.venus.model.DemoRequestStatus;
import com.slotizen.venus.service.DemoRequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/demo-requests")
public class DemoRequestController {
    
    private static final Logger logger = LoggerFactory.getLogger(DemoRequestController.class);
    
    @Autowired
    private DemoRequestService demoRequestService;
    
    @PostMapping
    public ResponseEntity<DemoRequestResponse> createDemoRequest(@Valid @RequestBody DemoRequestDto requestDto) {
        try {
            DemoRequest savedRequest = demoRequestService.createDemoRequest(requestDto);
            
            DemoRequestResponse response = DemoRequestResponse.success(
                savedRequest.getId(),
                "Demo request submitted successfully. We'll contact you within 24 hours."
            );
            
            return ResponseEntity.ok(response);
            
        } catch (DuplicateRequestException e) {
            logger.warn("Duplicate demo request attempt: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(DemoRequestResponse.error("Demo request already exists for this email. Please check your email or try again tomorrow."));
                
        } catch (Exception e) {
            logger.error("Error creating demo request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(DemoRequestResponse.error("Failed to submit demo request. Please try again later."));
        }
    }
    
    @GetMapping
    public ResponseEntity<List<DemoRequest>> getAllDemoRequests() {
        try {
            List<DemoRequest> requests = demoRequestService.getAllDemoRequests();
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            logger.error("Error retrieving demo requests", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<DemoRequest>> getDemoRequestsByStatus(@PathVariable DemoRequestStatus status) {
        try {
            List<DemoRequest> requests = demoRequestService.getDemoRequestsByStatus(status);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            logger.error("Error retrieving demo requests by status", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DemoRequest> getDemoRequestById(@PathVariable Long id) {
        try {
            return demoRequestService.getDemoRequestById(id)
                .map(request -> ResponseEntity.ok(request))
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error retrieving demo request by id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<DemoRequest> updateDemoRequestStatus(
            @PathVariable Long id,
            @RequestParam DemoRequestStatus status) {
        try {
            DemoRequest updatedRequest = demoRequestService.updateDemoRequestStatus(id, status);
            return ResponseEntity.ok(updatedRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error updating demo request status", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<DemoRequest>> getDemoRequestsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            LocalDateTime start = LocalDateTime.parse(startDate);
            LocalDateTime end = LocalDateTime.parse(endDate);
            
            List<DemoRequest> requests = demoRequestService.getDemoRequestsByDateRange(start, end);
            return ResponseEntity.ok(requests);
        } catch (Exception e) {
            logger.error("Error retrieving demo requests by date range", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}