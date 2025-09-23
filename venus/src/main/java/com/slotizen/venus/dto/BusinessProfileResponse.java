package com.slotizen.venus.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public class BusinessProfileResponse {
    public boolean success;
    public String message;
    public Data data;

    public static class Data {
        public UUID businessId;
        public String businessName;
        public String slug;
        public ZonedDateTime createdAt;
    }
    // Getters and setters
}
