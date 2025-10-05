package com.slotizen.venus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class UpdateClientRequest {
    
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;
    
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must be less than 255 characters")
    private String email;
    
    @Size(max = 50, message = "Phone must be less than 50 characters")
    private String phone;
    
    @Size(max = 5000, message = "Notes must be less than 5000 characters")
    private String notes;
    
    private String status;
    
    private LocalDate dateOfBirth;
    
    private List<String> tags;
    
    private List<Long> favoriteServices;
    
    public UpdateClientRequest() {}
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    
    public List<Long> getFavoriteServices() { return favoriteServices; }
    public void setFavoriteServices(List<Long> favoriteServices) { this.favoriteServices = favoriteServices; }
}