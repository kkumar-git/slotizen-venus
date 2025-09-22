package com.slotizen.venus.dto;

public class RegisterResponse {
    private boolean success;
    private String message;
    private RegisterResponseData data;

    // Default constructor
    public RegisterResponse() {}

    // Constructor
    public RegisterResponse(boolean success, String message, RegisterResponseData data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Static factory methods
    public static RegisterResponse success(String message, RegisterResponseData data) {
    	return new RegisterResponse(true, message, data);
    }

    public static RegisterResponse failure(String message) {
        return new RegisterResponse(false, message, null);
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

    public RegisterResponseData getData() {
        return data;
    }

    public void setData(RegisterResponseData data) {
        this.data = data;
    }

    // Inner class for response data
    public static class RegisterResponseData {
        private String userId;
        private String email;
        private boolean otpSent;
        private String verificationMethod;

        // Default constructor
        public RegisterResponseData() {}

        // Constructor
        public RegisterResponseData(String userId, String email, boolean otpSent, String verificationMethod) {
            this.userId = userId;
            this.email = email;
            this.otpSent = otpSent;
            this.verificationMethod = verificationMethod;
        }

        // Getters and setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isOtpSent() {
            return otpSent;
        }

        public void setOtpSent(boolean otpSent) {
            this.otpSent = otpSent;
        }

        public String getVerificationMethod() {
            return verificationMethod;
        }

        public void setVerificationMethod(String verificationMethod) {
            this.verificationMethod = verificationMethod;
        }
    }
}