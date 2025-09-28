package com.slotizen.venus.dto;

public class LogoUploadResponse {
    private boolean success;
    private String message;
    private String logoUrl;

    public LogoUploadResponse(boolean success, String message, String logoUrl) {
        this.success = success;
        this.message = message;
        this.logoUrl = logoUrl;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getLogoUrl() { return logoUrl; }
}