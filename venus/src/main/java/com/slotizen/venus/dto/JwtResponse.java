package com.slotizen.venus.dto;

public class JwtResponse {
    private boolean success;
    private String message;
    private String accessToken;
    private String refreshToken;
    private Object data;

    public JwtResponse() {}

    public JwtResponse(boolean success, String message, String accessToken, String refreshToken, Object data) {
        this.success = success;
        this.message = message;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.data = data;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}
