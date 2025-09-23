package com.slotizen.venus.dto;

import java.util.Map;
import java.util.UUID;

public class BusinessHoursResponse {
    public boolean success;
    public String message;
    public Data data;
    public static class Data {
        public UUID businessId;
        public Map<String, BusinessHoursRequest.DayHours> businessHours;
    }
    // Getters and setters
}
