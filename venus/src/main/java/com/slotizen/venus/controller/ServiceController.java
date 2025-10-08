package com.slotizen.venus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slotizen.venus.dto.CreateServiceRequest;
import com.slotizen.venus.dto.ServiceDto;
import com.slotizen.venus.dto.UpdateServiceRequest;
import com.slotizen.venus.model.ServiceStatus;
import com.slotizen.venus.service.ServiceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/business/{businessId}/services")
public class ServiceController {
    
    @Autowired
    private ServiceService serviceService;
    
    /**
     * GET /business/{businessId}/services - Get all services for the specified business
     * Query parameters:
     * - status: filter by service status (active/inactive)
     * - search: search by name or category
     */
    @GetMapping
    public ResponseEntity<List<ServiceDto>> getAllServices(
            @PathVariable(name = "businessId") Long businessId,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "search", required = false) String search) {
        
        List<ServiceDto> services;
        
        if (search != null && !search.trim().isEmpty()) {
            services = serviceService.searchServices(businessId, search.trim());
        } else if (status != null && !status.trim().isEmpty()) {
            try {
                ServiceStatus serviceStatus = ServiceStatus.fromString(status);
                services = serviceService.getServicesByStatus(businessId, serviceStatus);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            services = serviceService.getAllServices(businessId);
        }
        
        return ResponseEntity.ok(services);
    }
    
    /**
     * GET /business/{businessId}/services/{id} - Get service by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> getServiceById(
            @PathVariable(name = "businessId") Long businessId,
            @PathVariable(name = "id") Long id) {
        ServiceDto service = serviceService.getServiceById(businessId, id);
        return ResponseEntity.ok(service);
    }
    
    /**
     * POST /business/{businessId}/services - Create a new service
     */
    @PostMapping
    public ResponseEntity<ServiceDto> createService(
            @PathVariable(name = "businessId") Long businessId,
            @Valid @RequestBody CreateServiceRequest request) {
        ServiceDto createdService = serviceService.createService(businessId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdService);
    }
    
    /**
     * PUT /business/{businessId}/services/{id} - Update an existing service
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServiceDto> updateService(
            @PathVariable(name = "businessId") Long businessId,
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody UpdateServiceRequest request) {
        ServiceDto updatedService = serviceService.updateService(businessId, id, request);
        return ResponseEntity.ok(updatedService);
    }
    
    /**
     * DELETE /business/{businessId}/services/{id} - Delete a service
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(
            @PathVariable(name = "businessId") Long businessId,
            @PathVariable(name = "id") Long id) {
        serviceService.deleteService(businessId, id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * GET /business/{businessId}/services/{id}/statistics - Get service statistics
     */
    @GetMapping("/{id}/statistics")
    public ResponseEntity<ServiceDto> getServiceStatistics(
            @PathVariable(name = "businessId") Long businessId,
            @PathVariable(name = "id") Long id) {
        ServiceDto serviceWithStats = serviceService.getServiceStatistics(businessId, id);
        return ResponseEntity.ok(serviceWithStats);
    }
}