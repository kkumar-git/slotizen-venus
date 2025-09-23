package com.slotizen.venus.dto;

import java.util.Map;

public class BusinessHoursRequest {
    public Map<String, DayHours> businessHours;
    public String timezone;
    public static class DayHours {
        public Boolean isOpen;
        public String openTime;
        public String closeTime;
    }
    // Getters and setters
}
