package com.slotizen.venus.dto;

public class BusinessHoursRequest {
    public Hours businessHours;

    public static class Hours {
        public Day monday;
        public Day tuesday;
        public Day wednesday;
        public Day thursday;
        public Day friday;
        public Day saturday;
        public Day sunday;
    }

    public static class Day {
        public Boolean isOpen;
        public String openTime;   // format HH:mm
        public String closeTime;  // format HH:mm
    }
  
}