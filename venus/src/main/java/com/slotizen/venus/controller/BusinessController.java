package com.slotizen.venus.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.slotizen.venus.dto.BusinessHoursRequest;
import com.slotizen.venus.dto.BusinessHoursResponse;
import com.slotizen.venus.dto.BusinessProfileRequest;
import com.slotizen.venus.dto.BusinessProfileResponse;
import com.slotizen.venus.dto.DepartmentDto;
import com.slotizen.venus.dto.DepartmentResponse;
import com.slotizen.venus.dto.DepartmentsRequest;
import com.slotizen.venus.dto.LogoUploadResponse;
import com.slotizen.venus.dto.ServicesRequest;
import com.slotizen.venus.dto.SingleStaffRequest;
import com.slotizen.venus.dto.StaffDto;
import com.slotizen.venus.dto.StaffRequest;
import com.slotizen.venus.dto.StaffResponse;
import com.slotizen.venus.model.ServiceEntity;
import com.slotizen.venus.service.BusinessService;
import com.slotizen.venus.service.DepartmentService;
import com.slotizen.venus.util.SecurityUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;
    
    @Autowired
    private DepartmentService departmentService;

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
    public ResponseEntity<BusinessProfileResponse> updateProfile(@PathVariable Long businessId,
            @RequestBody BusinessProfileRequest request) {
        return ResponseEntity.ok(businessService.createOrUpdateProfile(request, businessId));
    }

    @PostMapping("/{businessId}/hours")
    public ResponseEntity<BusinessHoursResponse> setupHours(@PathVariable("businessId") Long businessId,
            @RequestBody BusinessHoursRequest request) {
        return ResponseEntity.ok(businessService.setupBusinessHours(businessId, request));
    }

    @GetMapping("/{businessId}/hours")
    public ResponseEntity<BusinessHoursResponse> getHours(@PathVariable("businessId") Long businessId) {
        return ResponseEntity.ok(businessService.getBusinessHours(businessId));
    }

    @PostMapping(value = "/{businessId}/logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public LogoUploadResponse uploadLogo(
            @PathVariable Long businessId,
            @RequestPart("file") MultipartFile file,
            Authentication authentication) {
        // Extract userId from auth principal or token (adjust to your security context)
        Long userId = extractUserId(authentication);
        return businessService.uploadLogo(userId, businessId, file);
    }

    @PostMapping("/{businessId}/services/setup")
    public ResponseEntity<?> saveServices(
            @PathVariable("businessId") Long businessId,
            @Valid @RequestBody ServicesRequest request,
            Authentication authentication) {
        
        try {
            // Validate business ownership/access
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            // Save services
            List<ServiceEntity> savedServices = businessService.saveServices(businessId, request.getServices());
            
            return ResponseEntity.ok(Map.of(
                "message", "Services saved successfully",
                "services", savedServices
            ));
            
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("message", "Access denied"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Failed to save services"));
        }
    }

    private void validateBusinessAccess(Long userId, Long businessId) {
        // TODO: Implement business ownership validation
        // For now, just check if user is authenticated
        if (userId == null) {
            throw new SecurityException("User not authenticated");
        }
    }

    private Long extractUserId(Authentication auth) {
        return SecurityUtils.getCurrentUserId();
    }
    
    // Staff Management Endpoints
    
    @PostMapping("/{businessId}/staff")
    public ResponseEntity<StaffResponse> createStaff(
            @PathVariable("businessId") Long businessId,
            @Valid @RequestBody StaffRequest request,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            List<StaffDto> createdStaff = new ArrayList<>();
            for (StaffRequest.StaffMemberRequest staffMember : request.getStaff()) {
                SingleStaffRequest singleRequest = convertToSingleStaffRequest(staffMember);
                StaffDto created = businessService.createStaffMember(businessId, singleRequest);
                createdStaff.add(created);
            }
            
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(StaffResponse.success("Staff members created successfully", createdStaff));
                
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(StaffResponse.error("Access denied"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(StaffResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(StaffResponse.error("Failed to create staff members"));
        }
    }
    
    @PostMapping("/{businessId}/staff/single")
    public ResponseEntity<StaffResponse> createStaffMember(
            @PathVariable("businessId") Long businessId,
            @Valid @RequestBody SingleStaffRequest request,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            StaffDto staffDto = businessService.createStaffMember(businessId, request);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(StaffResponse.success("Staff member created successfully", staffDto));
                
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(StaffResponse.error("Access denied"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(StaffResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(StaffResponse.error("Failed to create staff member"));
        }
    }
    
    @GetMapping("/{businessId}/staff")
    public ResponseEntity<StaffResponse> getAllStaff(
            @PathVariable("businessId") Long businessId,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            List<StaffDto> staff = businessService.getAllStaff(businessId);
            return ResponseEntity.ok(StaffResponse.success("Staff retrieved successfully", staff));
            
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(StaffResponse.error("Access denied"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(StaffResponse.error("Failed to retrieve staff"));
        }
    }
    
    @GetMapping("/{businessId}/staff/{staffId}")
    public ResponseEntity<StaffResponse> getStaffById(
            @PathVariable("businessId") Long businessId,
            @PathVariable("staffId") Long staffId,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            StaffDto staffDto = businessService.getStaffById(businessId, staffId);
            return ResponseEntity.ok(StaffResponse.success("Staff member retrieved successfully", staffDto));
            
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(StaffResponse.error("Access denied"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(StaffResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(StaffResponse.error("Failed to retrieve staff member"));
        }
    }
    
    @PutMapping("/{businessId}/staff/{staffId}")
    public ResponseEntity<StaffResponse> updateStaffMember(
            @PathVariable("businessId") Long businessId,
            @PathVariable("staffId") Long staffId,
            @Valid @RequestBody SingleStaffRequest request,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            StaffDto staffDto = businessService.updateStaffMember(businessId, staffId, request);
            return ResponseEntity.ok(StaffResponse.success("Staff member updated successfully", staffDto));
            
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(StaffResponse.error("Access denied"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(StaffResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(StaffResponse.error("Failed to update staff member"));
        }
    }
    
    @DeleteMapping("/{businessId}/staff/{staffId}")
    public ResponseEntity<StaffResponse> deleteStaffMember(
            @PathVariable("businessId") Long businessId,
            @PathVariable("staffId") Long staffId,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            businessService.deleteStaffMember(businessId, staffId);
            return ResponseEntity.ok(StaffResponse.success("Staff member deleted successfully"));
            
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(StaffResponse.error("Access denied"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(StaffResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(StaffResponse.error("Failed to delete staff member"));
        }
    }
    
    @GetMapping("/{businessId}/staff/by-service")
    public ResponseEntity<StaffResponse> getStaffByService(
            @PathVariable("businessId") Long businessId,
            @RequestParam("serviceId") Long serviceId,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            List<StaffDto> staff = businessService.getStaffByService(businessId, serviceId);
            return ResponseEntity.ok(StaffResponse.success("Staff retrieved successfully", staff));
            
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(StaffResponse.error("Access denied"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(StaffResponse.error("Failed to retrieve staff"));
        }
    }
    
    // Department Management Endpoints
    
    @PostMapping("/{businessId}/departments")
    public ResponseEntity<?> createDepartments(
            @PathVariable(name = "businessId") Long businessId,
            @Valid @RequestBody DepartmentsRequest request,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            List<DepartmentResponse> departments = departmentService.createDepartments(businessId, request.getDepartments());
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                    "message", "Departments created successfully",
                    "departments", departments
                ));
                
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("message", "Access denied"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }
    
    @PostMapping("/{businessId}/departments/single")
    public ResponseEntity<?> createSingleDepartment(
            @PathVariable(name = "businessId") Long businessId,
            @Valid @RequestBody DepartmentDto request,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            DepartmentResponse department = departmentService.createSingleDepartment(businessId, request);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                    "message", "Department created successfully",
                    "department", department
                ));
                
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("message", "Access denied"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }
    
    @GetMapping("/{businessId}/departments")
    public ResponseEntity<?> getDepartments(
            @PathVariable(name = "businessId") Long businessId,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            List<DepartmentResponse> departments = departmentService.getDepartmentsByBusinessId(businessId);
            return ResponseEntity.ok(Map.of("departments", departments));
            
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("message", "Access denied"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Failed to retrieve departments"));
        }
    }
    
    @GetMapping("{businessId}/departments/{departmentId}")
    public ResponseEntity<?> getDepartmentById(
            @PathVariable(name = "businessId") Long businessId,
            @PathVariable(name = "departmentId") Long departmentId,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            DepartmentResponse department = departmentService.getDepartmentById(departmentId, businessId);
            return ResponseEntity.ok(department);
            
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("message", "Access denied"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", e.getMessage()));
        }
    }
    
    @PutMapping("{businessId}/departments/{departmentId}")
    public ResponseEntity<?> updateDepartment(
            @PathVariable(name = "businessId") Long businessId,
            @PathVariable(name = "departmentId") Long departmentId,
            @Valid @RequestBody DepartmentDto request,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            DepartmentResponse department = departmentService.updateDepartment(departmentId, businessId, request);
            return ResponseEntity.ok(department);
            
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("message", "Access denied"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }
    
    @DeleteMapping("{businessId}/departments/{departmentId}")
    public ResponseEntity<?> deleteDepartment(
            @PathVariable(name = "businessId") Long businessId,
            @PathVariable(name = "departmentId") Long departmentId,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            departmentService.deleteDepartment(departmentId, businessId);
            return ResponseEntity.ok(Map.of("message", "Department deleted successfully"));
            
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("message", "Access denied"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }
    
    @PostMapping("{businessId}/departments/{departmentId}/status")
    public ResponseEntity<?> toggleDepartmentStatus(
            @PathVariable Long departmentId,
            @PathVariable Long businessId,
            @RequestParam Boolean isActive,
            Authentication authentication) {
        try {
            Long userId = extractUserId(authentication);
            validateBusinessAccess(userId, businessId);
            
            DepartmentResponse department = departmentService.toggleDepartmentStatus(departmentId, businessId, isActive);
            return ResponseEntity.ok(department);
            
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("message", "Access denied"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
        }
    }
    
    private SingleStaffRequest convertToSingleStaffRequest(StaffRequest.StaffMemberRequest member) {
        SingleStaffRequest request = new SingleStaffRequest(
            member.getFirstName(),
            member.getLastName(), 
            member.getEmail(),
            member.getPhone(),
            member.getRole(),
            member.getServices()
        );
        request.setDepartmentId(member.getDepartmentId());
        request.setDepartmentName(member.getDepartmentName());
        return request;
    }
}
