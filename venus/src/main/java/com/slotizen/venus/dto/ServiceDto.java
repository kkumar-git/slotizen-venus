package com.slotizen.venus.dto;

import java.time.LocalDateTime;

import com.slotizen.venus.model.ServiceStatus;

public class ServiceDto {
    private Long id;
    private String name;
    private String description;
    private String duration;
    private String price;
    private String category;
    private ServiceStatus status;
    private Long businessId;
    private Long departmentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Service statistics
    private Long bookingsCount = 0L;
    private String totalRevenue = "0.00";
    private Double averageRating = 0.0;
    
    // Constructors
    public ServiceDto() {}
    
    public ServiceDto(Long id, String name, String description, String duration, String price, 
                     String category, ServiceStatus status, Long businessId, Long departmentId,
                     LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.category = category;
        this.status = status;
        this.businessId = businessId;
        this.departmentId = departmentId;
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
    
    public Long getBusinessId() {
        return businessId;
    }
    
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
    
    public Long getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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
    
    public Long getBookingsCount() {
        return bookingsCount;
    }
    
    public void setBookingsCount(Long bookingsCount) {
        this.bookingsCount = bookingsCount;
    }
    
    public String getTotalRevenue() {
        return totalRevenue;
    }
    
    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    
    public Double getAverageRating() {
        return averageRating;
    }
    
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}