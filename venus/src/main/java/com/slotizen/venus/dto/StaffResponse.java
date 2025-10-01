package com.slotizen.venus.dto;

import java.util.List;

public class StaffResponse {
    
    private boolean success;
    private String message;
    private List<StaffDto> data;
    private StaffDto staffMember;
    
    // Constructors
    public StaffResponse() {}
    
    public StaffResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public StaffResponse(boolean success, String message, List<StaffDto> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    
    public StaffResponse(boolean success, String message, StaffDto staffMember) {
        this.success = success;
        this.message = message;
        this.staffMember = staffMember;
    }
    
    // Static factory methods
    public static StaffResponse success(String message, List<StaffDto> data) {
        return new StaffResponse(true, message, data);
    }
    
    public static StaffResponse success(String message, StaffDto staffMember) {
        return new StaffResponse(true, message, staffMember);
    }
    
    public static StaffResponse success(String message) {
        return new StaffResponse(true, message);
    }
    
    public static StaffResponse error(String message) {
        return new StaffResponse(false, message);
    }
    
    // Getters and setters
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public List<StaffDto> getData() {
        return data;
    }
    
    public void setData(List<StaffDto> data) {
        this.data = data;
    }
    
    public StaffDto getStaffMember() {
        return staffMember;
    }
    
    public void setStaffMember(StaffDto staffMember) {
        this.staffMember = staffMember;
    }
}