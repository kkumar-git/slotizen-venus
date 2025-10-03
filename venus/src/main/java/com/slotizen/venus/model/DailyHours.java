package com.slotizen.venus.model;

import java.time.LocalTime;

import jakarta.persistence.Embeddable;

@Embeddable
public class DailyHours {
    private Boolean isOpen;
    private String openTime;
    private String closeTime;

    public DailyHours() {}
    public DailyHours(Boolean isOpen, String openTime, String closeTime) {
        this.isOpen = isOpen;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
    public Boolean getIsOpen() { return isOpen; }
    public void setIsOpen(Boolean open) { this.isOpen = open; }
    public String getOpenTime() { return openTime; }
    public void setOpenTime(String openTime) { this.openTime = openTime; }
    public String getCloseTime() { return closeTime; }
    public void setCloseTime(String closeTime) { this.closeTime = closeTime; }
}

