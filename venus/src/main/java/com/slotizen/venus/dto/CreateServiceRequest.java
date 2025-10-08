package com.slotizen.venus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateServiceRequest {
    @NotBlank(message = "Service name is required")
    @Size(min = 2, max = 100, message = "Service name must be between 2 and 100 characters")
    private String name;
    
    @NotBlank(message = "Description is required") 
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;
    
    @NotBlank(message = "Duration is required")
    @Pattern(regexp = "^(15|30|45|60|90|120|180|240)$", message = "Duration must be 15, 30, 45, 60, 90, 120, 180, or 240 minutes")
    private String duration;
    
    @NotBlank(message = "Price is required")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Price must be a valid decimal number (e.g., 25.00)")
    private String price;
    
    @NotBlank(message = "Category is required")
    //@Pattern(regexp = "^(hair-services|skin-care|nail-care|massage-therapy|medical-treatment|fitness-training|consultation|other)$", 
    //          message = "Category must be one of: hair-services, skin-care, nail-care, massage-therapy, medical-treatment, fitness-training, consultation, other")
    private String category;
    
    private Long departmentId;
    
    // Constructors
    public CreateServiceRequest() {}
    
    public CreateServiceRequest(String name, String description, String duration, String price, String category) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.category = category;
    }
    
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
    
    public Long getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}