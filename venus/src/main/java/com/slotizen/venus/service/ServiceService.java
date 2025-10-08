package com.slotizen.venus.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slotizen.venus.dto.CreateServiceRequest;
import com.slotizen.venus.dto.ServiceDto;
import com.slotizen.venus.dto.UpdateServiceRequest;
import com.slotizen.venus.exception.DuplicateResourceException;
import com.slotizen.venus.exception.ResourceNotFoundException;
import com.slotizen.venus.model.ServiceEntity;
import com.slotizen.venus.model.ServiceStatus;
import com.slotizen.venus.repository.ServiceRepository;

@Service
@Transactional
public class ServiceService {
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    // Get all services for specified business
    public List<ServiceDto> getAllServices(Long businessId) {
        List<ServiceEntity> services = serviceRepository.findByBusinessIdOrderByCreatedAtDesc(businessId);
        return services.stream()
                      .map(this::convertToDto)
                      .collect(Collectors.toList());
    }
    
    // Get service by ID
    public ServiceDto getServiceById(Long businessId, Long id) {
        ServiceEntity service = serviceRepository.findByBusinessIdAndId(businessId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with ID: " + id));
        return convertToDto(service);
    }
    
    // Create new service
    public ServiceDto createService(Long businessId, CreateServiceRequest request) {
        // Check for duplicate service name in the same business
        List<ServiceEntity> existingServices = serviceRepository.findByBusinessIdOrderByCreatedAtDesc(businessId);
        boolean nameExists = existingServices.stream()
                .anyMatch(service -> service.getName().toLowerCase().equals(request.getName().toLowerCase()));
        
        if (nameExists) {
            throw new DuplicateResourceException("A service with the name '" + request.getName() + "' already exists");
        }
        
        ServiceEntity service = new ServiceEntity();
        service.setName(request.getName());
        service.setDescription(request.getDescription());
        service.setDuration(request.getDuration());
        service.setPrice(request.getPrice());
        service.setCategory(request.getCategory());
        service.setStatus(ServiceStatus.ACTIVE);
        service.setBusinessId(businessId);
        service.setDepartmentId(request.getDepartmentId());
        
        ServiceEntity savedService = serviceRepository.save(service);
        return convertToDto(savedService);
    }
    
    // Update service
    public ServiceDto updateService(Long businessId, Long id, UpdateServiceRequest request) {
        ServiceEntity service = serviceRepository.findByBusinessIdAndId(businessId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with ID: " + id));
        
        // Check for duplicate name if name is being updated
        if (request.getName() != null && !request.getName().equals(service.getName())) {
            List<ServiceEntity> existingServices = serviceRepository.findByBusinessIdOrderByCreatedAtDesc(businessId);
            boolean nameExists = existingServices.stream()
                    .anyMatch(s -> s.getId() != id && s.getName().toLowerCase().equals(request.getName().toLowerCase()));
            
            if (nameExists) {
                throw new DuplicateResourceException("A service with the name '" + request.getName() + "' already exists");
            }
        }
        
        // Update fields if provided
        if (request.getName() != null) {
            service.setName(request.getName());
        }
        if (request.getDescription() != null) {
            service.setDescription(request.getDescription());
        }
        if (request.getDuration() != null) {
            service.setDuration(request.getDuration());
        }
        if (request.getPrice() != null) {
            service.setPrice(request.getPrice());
        }
        if (request.getCategory() != null) {
            service.setCategory(request.getCategory());
        }
        if (request.getStatus() != null) {
            service.setStatus(request.getStatus());
        }
        if (request.getDepartmentId() != null) {
            service.setDepartmentId(request.getDepartmentId());
        }
        
        ServiceEntity updatedService = serviceRepository.save(service);
        return convertToDto(updatedService);
    }
    
    // Delete service
    public void deleteService(Long businessId, Long id) {
        ServiceEntity service = serviceRepository.findByBusinessIdAndId(businessId, id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with ID: " + id));
        
        serviceRepository.delete(service);
    }
    
    // Search services
    public List<ServiceDto> searchServices(Long businessId, String search) {
        List<ServiceEntity> services = serviceRepository.searchServices(businessId, search);
        return services.stream()
                      .map(this::convertToDto)
                      .collect(Collectors.toList());
    }
    
    // Get services by status
    public List<ServiceDto> getServicesByStatus(Long businessId, ServiceStatus status) {
        List<ServiceEntity> services = serviceRepository.findByBusinessIdAndStatus(businessId, status);
        return services.stream()
                      .map(this::convertToDto)
                      .collect(Collectors.toList());
    }
    
    // Get service statistics
    public ServiceDto getServiceStatistics(Long businessId, Long id) {
        ServiceDto service = getServiceById(businessId, id);
        
        // Calculate statistics (placeholder - integrate with booking system later)
        service.setBookingsCount(calculateBookingsCount(id));
        service.setTotalRevenue(calculateTotalRevenue(id));
        service.setAverageRating(calculateAverageRating(id));
        
        return service;
    }
    
    // Private helper methods
    private ServiceDto convertToDto(ServiceEntity service) {
        ServiceDto dto = new ServiceDto(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getDuration(),
                service.getPrice(),
                service.getCategory(),
                service.getStatus(),
                service.getBusinessId(),
                service.getDepartmentId(),
                service.getCreatedAt(),
                service.getUpdatedAt()
        );
        
        // Add statistics if needed
        dto.setBookingsCount(calculateBookingsCount(service.getId()));
        dto.setTotalRevenue(calculateTotalRevenue(service.getId()));
        dto.setAverageRating(calculateAverageRating(service.getId()));
        
        return dto;
    }
    
    // Statistics calculation methods (to be integrated with booking system)
    private Long calculateBookingsCount(Long serviceId) {
        // TODO: Integrate with booking repository when available
        // return bookingRepository.countByServiceId(serviceId);
        return 0L;
    }
    
    private String calculateTotalRevenue(Long serviceId) {
        // TODO: Integrate with booking repository when available
        // BigDecimal revenue = bookingRepository.sumRevenueByServiceId(serviceId);
        // return revenue != null ? revenue.toString() : "0.00";
        return "0.00";
    }
    
    private Double calculateAverageRating(Long serviceId) {
        // TODO: Integrate with rating/review system when available
        // return reviewRepository.averageRatingByServiceId(serviceId);
        return 0.0;
    }
}