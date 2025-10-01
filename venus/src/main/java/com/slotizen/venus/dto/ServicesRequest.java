package com.slotizen.venus.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ServicesRequest {
    @Valid
    @NotNull
    @Size(min = 1, message = "At least one service is required")
    private List<ServiceDto> services;
    
    // Constructors
    public ServicesRequest() {}
    
    public ServicesRequest(List<ServiceDto> services) {
        this.services = services;
    }
    
    // Getters and setters
    public List<ServiceDto> getServices() {
        return services;
    }
    
    public void setServices(List<ServiceDto> services) {
        this.services = services;
    }
}