package com.slotizen.venus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ServiceStatus {
    ACTIVE("active"),
    INACTIVE("inactive");
    
    private final String value;
    
    ServiceStatus(String value) {
        this.value = value;
    }
    
    @JsonProperty
    public String getValue() {
        return value.toLowerCase();
    }
    
    public static ServiceStatus fromString(String value) {
        for (ServiceStatus status : ServiceStatus.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid service status: " + value);
    }
}