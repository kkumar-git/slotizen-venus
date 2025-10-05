package com.slotizen.venus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ClientStatus {
    ACTIVE("active"),
    INACTIVE("inactive");
    
    private final String value;
    
    ClientStatus(String value) {
        this.value = value;
    }
    
    @JsonProperty
    public String getValue() {
        return value.toLowerCase();
    }
    
    public static ClientStatus fromString(String value) {
        for (ClientStatus status : ClientStatus.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid client status: " + value);
    }
}