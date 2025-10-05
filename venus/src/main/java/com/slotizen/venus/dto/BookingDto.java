package com.slotizen.venus.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingDto {
    private Long id;
    private String client;
    private String clientAvatar;
    private String service;
    private String staff;
    private LocalDate date;
    private String time;
    private Integer duration;
    private String status;
    private BigDecimal price;
    private String notes;
    
    // Constructors
    public BookingDto() {}
    
    public BookingDto(Long id, String client, String clientAvatar, String service, String staff,
                     LocalDate date, String time, Integer duration, String status,
                     BigDecimal price, String notes) {
        this.id = id;
        this.client = client;
        this.clientAvatar = clientAvatar;
        this.service = service;
        this.staff = staff;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.status = status;
        this.price = price;
        this.notes = notes;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }
    
    public String getClientAvatar() { return clientAvatar; }
    public void setClientAvatar(String clientAvatar) { this.clientAvatar = clientAvatar; }
    
    public String getService() { return service; }
    public void setService(String service) { this.service = service; }
    
    public String getStaff() { return staff; }
    public void setStaff(String staff) { this.staff = staff; }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}