package com.slotizen.venus.dto;

import java.util.UUID;

public class BusinessHoursResponse {
    public boolean success;
    public String message;
    public Data data;
    public static class Data {
        public UUID businessId;
        public BusinessHoursRequest.Hours businessHours;
    }
    
}
