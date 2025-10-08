package com.slotizen.venus.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import com.slotizen.venus.model.ServiceStatus;

public class UpdateServiceRequest {
    @Size(min = 2, max = 100, message = "Service name must be between 2 and 100 characters")
    private String name;
    
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;
    
    @Pattern(regexp = "^(15|30|45|60|90|120|180|240)$", message = "Duration must be 15, 30, 45, 60, 90, 120, 180, or 240 minutes")
    private String duration;
    
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Price must be a valid decimal number (e.g., 25.00)")
    private String price;
    
    //@Pattern(regexp = "^(hair-services|skin-care|nail-care|massage-therapy|medical-treatment|fitness-training|consultation|other)$", 
     //        message = "Category must be one of: hair-services, skin-care, nail-care, massage-therapy, medical-treatment, fitness-training, consultation, other")
    private String category;
    
    private ServiceStatus status;
    
    private Long departmentId;
    
    // Constructors
    public UpdateServiceRequest() {}
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getPrice() {
        return price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public ServiceStatus getStatus() {
        return status;
    }
    
    public void setStatus(ServiceStatus status) {
        this.status = status;
    }
    
    public Long getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}