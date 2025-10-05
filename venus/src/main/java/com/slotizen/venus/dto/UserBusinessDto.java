package com.slotizen.venus.dto;

import java.util.UUID;

public class UserBusinessDto {
    private Long userId;
    private UUID businessId;
    
    // Constructors
    public UserBusinessDto() {}
    
    public UserBusinessDto(Long userId, UUID businessId) {
        this.userId = userId;
        this.businessId = businessId;
    }
    
    // Getters and setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public UUID getBusinessId() { return businessId; }
    public void setBusinessId(UUID businessId) { this.businessId = businessId; }
}