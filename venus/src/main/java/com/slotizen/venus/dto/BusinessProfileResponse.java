package com.slotizen.venus.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessProfileResponse {
    public boolean success;
    public String message;
    public UUID businessId;
    public Data data;

    public static class Data {
        public UUID businessId;
        public String businessName;
        public String slug;
        public ZonedDateTime createdAt;
        public String logoUrl;
        public String businessType;
        public String description;
        public String address;
        public String city;
        public String state;
        public String zipCode;
        public String phone;
        public String website;
        public String timezone;
        public boolean active;
        public Integer competitionLevel;
        public boolean completed;
    }
    
}
