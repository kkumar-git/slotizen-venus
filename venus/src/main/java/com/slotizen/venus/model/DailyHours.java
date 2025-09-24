package com.slotizen.venus.model;

import java.time.LocalTime;

import jakarta.persistence.Embeddable;

@Embeddable
public class DailyHours {
    private Boolean isOpen;
    private LocalTime openTime;
    private LocalTime closeTime;

    public DailyHours() {}
    public DailyHours(Boolean isOpen, LocalTime openTime, LocalTime closeTime) {
        this.isOpen = isOpen;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
    public Boolean getIsOpen() { return isOpen; }
    public void setIsOpen(Boolean open) { this.isOpen = open; }
    public LocalTime getOpenTime() { return openTime; }
    public void setOpenTime(LocalTime openTime) { this.openTime = openTime; }
    public LocalTime getCloseTime() { return closeTime; }
    public void setCloseTime(LocalTime closeTime) { this.closeTime = closeTime; }
}

