package com.slotizen.venus.dto;

import java.time.LocalTime;

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
        public String openTime;   // format HH:mm:ss
        public String closeTime;  // format HH:mm
    }
  
}