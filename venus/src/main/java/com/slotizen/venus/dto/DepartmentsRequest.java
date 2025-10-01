package com.slotizen.venus.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class DepartmentsRequest {
    
    @Valid
    @NotEmpty(message = "At least one department is required")
    private List<DepartmentDto> departments;
    
    // Constructors
    public DepartmentsRequest() {}
    
    public DepartmentsRequest(List<DepartmentDto> departments) {
        this.departments = departments;
    }
    
    // Getters and setters
    public List<DepartmentDto> getDepartments() {
        return departments;
    }
    
    public void setDepartments(List<DepartmentDto> departments) {
        this.departments = departments;
    }
}