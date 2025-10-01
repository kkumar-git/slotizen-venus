package com.slotizen.venus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DemoRequestDto {
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    @Size(max = 255, message = "Email must be less than 255 characters")
    private String email;
    
    @NotBlank(message = "Company is required")
    @Size(max = 100, message = "Company must be less than 100 characters")
    private String company;
    
    @Size(max = 100, message = "Industry must be less than 100 characters")
    private String industry;
    
    @Size(max = 1000, message = "Message must be less than 1000 characters")
    private String message;
    
    // Constructors
    public DemoRequestDto() {}
    
    public DemoRequestDto(String name, String email, String company, String industry, String message) {
        this.name = name;
        this.email = email;
        this.company = company;
        this.industry = industry;
        this.message = message;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getIndustry() {
        return industry;
    }
    
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}