package com.slotizen.venus.dto;

public class DemoRequestResponse {
    
    private Long id;
    private String status;
    private String message;
    
    // Constructors
    public DemoRequestResponse() {}
    
    public DemoRequestResponse(Long id, String status, String message) {
        this.id = id;
        this.status = status;
        this.message = message;
    }
    
    // Static factory methods
    public static DemoRequestResponse success(Long id, String message) {
        return new DemoRequestResponse(id, "SUCCESS", message);
    }
    
    public static DemoRequestResponse error(String message) {
        return new DemoRequestResponse(null, "ERROR", message);
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}