package com.slotizen.venus.dto;

import java.util.UUID;

public class UserBusinessDto {
    private Long userId;
    private UUID businessId;
    private String businessName;
    private String businessLogo;
    private String businessType;
    
    // Constructors
    public UserBusinessDto() {}
    
    public UserBusinessDto(Long userId, UUID businessId) {
        this.userId = userId;
        this.businessId = businessId;
    }

    public UserBusinessDto(Long userId, UUID businessId, String businessName) {
        this.userId = userId;
        this.businessId = businessId;
        this.businessName = businessName;
    }

    public UserBusinessDto(Long userId, UUID businessId, String businessName, String businessLogo, String businessType) {
        this.userId = userId;
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessLogo = businessLogo;
        this.businessType = businessType;
    }
    
    // Getters and setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public UUID getBusinessId() { return businessId; }
    public void setBusinessId(UUID businessId) { this.businessId = businessId; }
    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getBusinessLogo() { return businessLogo; }
    public void setBusinessLogo(String businessLogo) { this.businessLogo = businessLogo; }
    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }
}