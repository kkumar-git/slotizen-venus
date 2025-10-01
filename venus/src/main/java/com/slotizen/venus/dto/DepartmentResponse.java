package com.slotizen.venus.dto;

import java.time.LocalDateTime;

public class DepartmentResponse {
    
    private Long id;
    private String businessId;
    private String name;
    private String description;
    private String color;
    private Boolean isActive;
    private Integer staffCount;
    private Integer servicesCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public DepartmentResponse() {}
    
    public DepartmentResponse(Long id, String businessId, String name, String description, String color, 
                            Boolean isActive, Integer staffCount, Integer servicesCount, 
                            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.businessId = businessId;
        this.name = name;
        this.description = description;
        this.color = color;
        this.isActive = isActive;
        this.staffCount = staffCount;
        this.servicesCount = servicesCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBusinessId() {
        return businessId;
    }
    
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    
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
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public Integer getStaffCount() {
        return staffCount;
    }
    
    public void setStaffCount(Integer staffCount) {
        this.staffCount = staffCount;
    }
    
    public Integer getServicesCount() {
        return servicesCount;
    }
    
    public void setServicesCount(Integer servicesCount) {
        this.servicesCount = servicesCount;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}