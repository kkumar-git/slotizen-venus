package com.slotizen.venus.dto;


public class BusinessHoursResponse {
    public boolean success;
    public String message;
    public Data data;
    public static class Data {
        public Long businessId;
        public BusinessHoursRequest.Hours businessHours;
    }
    
}
