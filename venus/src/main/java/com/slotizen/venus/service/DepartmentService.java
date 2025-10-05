package com.slotizen.venus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slotizen.venus.dto.DepartmentDto;
import com.slotizen.venus.dto.DepartmentResponse;
import com.slotizen.venus.exception.DepartmentDeletionException;
import com.slotizen.venus.exception.DepartmentNotFoundException;
import com.slotizen.venus.exception.DepartmentValidationException;
import com.slotizen.venus.model.Department;
import com.slotizen.venus.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentService {
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public List<DepartmentResponse> createDepartments(Long businessId, List<DepartmentDto> departmentDtos) {
        List<DepartmentResponse> createdDepartments = new ArrayList<>();
        
        for (DepartmentDto dto : departmentDtos) {
            // Check for duplicate names
            if (departmentRepository.existsByBusinessIdAndName(businessId, dto.getName())) {
                throw new DepartmentValidationException("Department with name '" + dto.getName() + "' already exists");
            }
            
            Department department = new Department(
                businessId,
                dto.getName().trim(),
                dto.getDescription().trim(),
                dto.getColor()
            );
            
            Department saved = departmentRepository.save(department);
            createdDepartments.add(convertToDepartmentResponse(saved, 0, 0));
        }
        
        return createdDepartments;
    }
    
    public List<DepartmentResponse> getDepartmentsByBusinessId(Long businessId) {
        List<Object[]> results = departmentRepository.findDepartmentsWithCounts(businessId);
        List<DepartmentResponse> responses = new ArrayList<>();
        
        for (Object[] result : results) {
            Department dept = (Department) result[0];
            Long staffCount = (Long) result[1];
            Long servicesCount = (Long) result[2];
            
            responses.add(convertToDepartmentResponse(dept, 
                staffCount != null ? staffCount.intValue() : 0,
                servicesCount != null ? servicesCount.intValue() : 0));
        }
        
        return responses;
    }

    public DepartmentResponse getDepartmentById(Long departmentId, Long businessId) {
        Department department = departmentRepository.findByIdAndBusinessId(departmentId, businessId)
            .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + departmentId));
        
        long staffCount = departmentRepository.countStaffByDepartmentId(departmentId);
        long servicesCount = departmentRepository.countServicesByDepartmentId(departmentId);
        
        return convertToDepartmentResponse(department, (int) staffCount, (int) servicesCount);
    }

    public DepartmentResponse updateDepartment(Long departmentId, Long businessId, DepartmentDto departmentDto) {
        Department department = departmentRepository.findByIdAndBusinessId(departmentId, businessId)
            .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + departmentId));
        
        // Check for duplicate names (excluding current department)
        if (departmentRepository.existsByBusinessIdAndNameAndIdNot(businessId, departmentDto.getName(), departmentId)) {
            throw new DepartmentValidationException("Department with name '" + departmentDto.getName() + "' already exists");
        }
        
        department.setName(departmentDto.getName().trim());
        department.setDescription(departmentDto.getDescription().trim());
        department.setColor(departmentDto.getColor());
        
        Department updated = departmentRepository.save(department);
        
        long staffCount = departmentRepository.countStaffByDepartmentId(departmentId);
        long servicesCount = departmentRepository.countServicesByDepartmentId(departmentId);
        
        return convertToDepartmentResponse(updated, (int) staffCount, (int) servicesCount);
    }

    public void deleteDepartment(Long departmentId, Long businessId) {
        Department department = departmentRepository.findByIdAndBusinessId(departmentId, businessId)
            .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + departmentId));
        
        // Check if department has assigned staff or services
        long staffCount = departmentRepository.countStaffByDepartmentId(departmentId);
        long servicesCount = departmentRepository.countServicesByDepartmentId(departmentId);
        
        if (staffCount > 0 || servicesCount > 0) {
            throw new DepartmentDeletionException("Cannot delete department that has assigned staff or services");
        }
        
        // Check if this is the last active department
        long activeDepartments = departmentRepository.countByBusinessIdAndIsActive(businessId, true);
        if (department.getIsActive() && activeDepartments <= 1) {
            throw new DepartmentDeletionException("Cannot delete the last active department");
        }
        
        departmentRepository.delete(department);
    }

    public DepartmentResponse toggleDepartmentStatus(Long departmentId, Long businessId, Boolean isActive) {
        Department department = departmentRepository.findByIdAndBusinessId(departmentId, businessId)
            .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + departmentId));
        
        // Check if trying to deactivate the last active department
        if (!isActive && department.getIsActive()) {
            long activeDepartments = departmentRepository.countByBusinessIdAndIsActive(businessId, true);
            if (activeDepartments <= 1) {
                throw new DepartmentValidationException("Cannot deactivate the last active department");
            }
        }
        
        department.setIsActive(isActive);
        Department updated = departmentRepository.save(department);
        
        long staffCount = departmentRepository.countStaffByDepartmentId(departmentId);
        long servicesCount = departmentRepository.countServicesByDepartmentId(departmentId);
        
        return convertToDepartmentResponse(updated, (int) staffCount, (int) servicesCount);
    }
    
    private DepartmentResponse convertToDepartmentResponse(Department department, int staffCount, int servicesCount) {
        return new DepartmentResponse(
            department.getId(),
            department.getBusinessId(),
            department.getName(),
            department.getDescription(),
            department.getColor(),
            department.getIsActive(),
            staffCount,
            servicesCount,
            department.getCreatedAt(),
            department.getUpdatedAt()
        );
    }
}