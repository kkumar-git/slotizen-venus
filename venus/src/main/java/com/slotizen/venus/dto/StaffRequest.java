package com.slotizen.venus.dto;

import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Wrapper class for multiple staff members
public class StaffRequest {
    
    @Valid
    @NotEmpty(message = "At least one staff member is required")
    private List<StaffMemberRequest> staff;
    
    // Constructors
    public StaffRequest() {}
    
    public StaffRequest(List<StaffMemberRequest> staff) {
        this.staff = staff;
    }
    
    // Getters and setters
    public List<StaffMemberRequest> getStaff() {
        return staff;
    }
    
    public void setStaff(List<StaffMemberRequest> staff) {
        this.staff = staff;
    }
    
    // Inner class for individual staff member
    public static class StaffMemberRequest {
        
        @NotBlank(message = "First name is required")
        @Size(max = 50, message = "First name cannot exceed 50 characters")
        private String firstName;
        
        @NotBlank(message = "Last name is required")
        @Size(max = 50, message = "Last name cannot exceed 50 characters")
        private String lastName;
        
        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email address")
        @Size(max = 100, message = "Email cannot exceed 100 characters")
        private String email;
        
        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^[+]?[(]?[\\d\\s\\-()]{10,20}$", message = "Please provide a valid phone number")
        private String phone;
        
        @NotBlank(message = "Role is required")
        @Size(max = 50, message = "Role cannot exceed 50 characters")
        private String role;
        
        private Set<String> services;
        private String id; // For updates (optional)
        
        // Constructors
        public StaffMemberRequest() {}
        
        public StaffMemberRequest(String firstName, String lastName, String email, String phone, String role, Set<String> services) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.role = role;
            this.services = services;
        }
        
        // Getters and setters
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }
        
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
        
        public Set<String> getServices() { return services; }
        public void setServices(Set<String> services) { this.services = services; }
        
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
    }
}