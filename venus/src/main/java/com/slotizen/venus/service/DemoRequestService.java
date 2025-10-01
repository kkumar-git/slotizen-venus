package com.slotizen.venus.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slotizen.venus.dto.DemoRequestDto;
import com.slotizen.venus.exception.DuplicateRequestException;
import com.slotizen.venus.model.DemoRequest;
import com.slotizen.venus.model.DemoRequestStatus;
import com.slotizen.venus.repository.DemoRequestRepository;

@Service
@Transactional
public class DemoRequestService {
    
    @Autowired
    private DemoRequestRepository demoRequestRepository;
    
    // TODO: Uncomment when EmailService is implemented
    // @Autowired
    // private EmailService emailService;
    
    public DemoRequest createDemoRequest(DemoRequestDto requestDto) {
        // Check for duplicate email within last 24 hours
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        Optional<DemoRequest> recentRequest = demoRequestRepository
            .findByEmailAndCreatedAtAfter(requestDto.getEmail(), yesterday);
            
        if (recentRequest.isPresent()) {
            throw new DuplicateRequestException("Demo request already exists for this email");
        }
        
        // Create new demo request
        DemoRequest demoRequest = new DemoRequest();
        demoRequest.setName(requestDto.getName().trim());
        demoRequest.setEmail(requestDto.getEmail().trim().toLowerCase());
        demoRequest.setCompany(requestDto.getCompany().trim());
        demoRequest.setIndustry(requestDto.getIndustry() != null ? requestDto.getIndustry().trim() : null);
        demoRequest.setMessage(requestDto.getMessage() != null ? requestDto.getMessage().trim() : null);
        demoRequest.setStatus(DemoRequestStatus.PENDING);
        
        DemoRequest saved = demoRequestRepository.save(demoRequest);
        
        // TODO: Send notification emails asynchronously when EmailService is implemented
        // emailService.sendDemoRequestNotification(saved);
        // emailService.sendDemoConfirmationToCustomer(saved);
        
        return saved;
    }
    
    public List<DemoRequest> getAllDemoRequests() {
        return demoRequestRepository.findAll();
    }
    
    public List<DemoRequest> getDemoRequestsByStatus(DemoRequestStatus status) {
        return demoRequestRepository.findByStatusOrderByCreatedAtDesc(status);
    }
    
    public List<DemoRequest> getDemoRequestsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return demoRequestRepository.findByDateRange(startDate, endDate);
    }
    
    public Optional<DemoRequest> getDemoRequestById(Long id) {
        return demoRequestRepository.findById(id);
    }
    
    public DemoRequest updateDemoRequestStatus(Long id, DemoRequestStatus status) {
        DemoRequest demoRequest = demoRequestRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Demo request not found with id: " + id));
        
        demoRequest.setStatus(status);
        return demoRequestRepository.save(demoRequest);
    }
}