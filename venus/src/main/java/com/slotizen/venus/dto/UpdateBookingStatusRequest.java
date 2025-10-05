package com.slotizen.venus.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdateBookingStatusRequest {
    @NotNull(message = "Status is required")
    @Pattern(regexp = "pending|confirmed|in-progress|completed|cancelled", 
             message = "Status must be one of: pending, confirmed, in-progress, completed, cancelled")
    private String status;
    
    // Constructors
    public UpdateBookingStatusRequest() {}
    
    public UpdateBookingStatusRequest(String status) {
        this.status = status;
    }
    
    // Getters and setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}