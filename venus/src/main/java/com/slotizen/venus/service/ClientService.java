package com.slotizen.venus.service;

import com.slotizen.venus.dto.ClientDto;
import com.slotizen.venus.dto.CreateClientRequest;
import com.slotizen.venus.dto.UpdateClientRequest;
import com.slotizen.venus.exception.DuplicateResourceException;
import com.slotizen.venus.exception.ResourceNotFoundException;
import com.slotizen.venus.model.Client;
import com.slotizen.venus.model.ClientStatus;
import com.slotizen.venus.repository.ClientRepository;
import com.slotizen.venus.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    /**
     * Get all clients for a business
     */
    public List<ClientDto> getAllClients(Long businessId) {
        List<Client> clients = clientRepository.findByBusinessIdOrderByCreatedAtDesc(businessId);
        return clients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Get clients filtered by status
     */
    public List<ClientDto> getClientsByStatus(Long businessId, String status) {
        ClientStatus clientStatus = ClientStatus.fromString(status);
        List<Client> clients = clientRepository.findByBusinessIdAndStatus(businessId, clientStatus);
        return clients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Search clients by name or email
     */
    public List<ClientDto> searchClients(Long businessId, String search) {
        List<Client> clients = clientRepository.searchClients(businessId, search);
        return clients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Get a single client by ID
     */
    public ClientDto getClientById(Long businessId, Long clientId) {
        Client client = clientRepository.findByBusinessIdAndId(businessId, clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return convertToDto(client);
    }
    
    /**
     * Create a new client
     */
    public ClientDto createClient(Long businessId, CreateClientRequest request) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        
        // Check for duplicate email
        if (clientRepository.existsByBusinessIdAndEmail(businessId, request.getEmail())) {
            throw new DuplicateResourceException("A client with this email already exists in this business");
        }
        
        Client client = new Client();
        client.setBusinessId(businessId);
        client.setName(request.getName().trim());
        client.setEmail(request.getEmail().trim().toLowerCase());
        client.setPhone(request.getPhone());
        client.setNotes(request.getNotes());
        client.setDateOfBirth(request.getDateOfBirth());
        client.setTags(request.getTags());
        client.setCreatedBy(currentUserId);
        client.setLastModifiedBy(currentUserId);
        
        Client savedClient = clientRepository.save(client);
        return convertToDto(savedClient);
    }
    
    /**
     * Update an existing client
     */
    public ClientDto updateClient(Long businessId, Long clientId, UpdateClientRequest request) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        
        Client client = clientRepository.findByBusinessIdAndId(businessId, clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        
        // Check for duplicate email (excluding current client)
        if (request.getEmail() != null && 
            !request.getEmail().equalsIgnoreCase(client.getEmail())) {
            if (clientRepository.existsByBusinessIdAndEmailAndIdNot(
                    businessId, request.getEmail(), clientId)) {
                throw new DuplicateResourceException("A client with this email already exists in this business");
            }
            client.setEmail(request.getEmail().trim().toLowerCase());
        }
        
        // Update fields if provided
        if (request.getName() != null) {
            client.setName(request.getName().trim());
        }
        if (request.getPhone() != null) {
            client.setPhone(request.getPhone());
        }
        if (request.getNotes() != null) {
            client.setNotes(request.getNotes());
        }
        if (request.getStatus() != null) {
            client.setStatus(ClientStatus.fromString(request.getStatus()));
        }
        if (request.getDateOfBirth() != null) {
            client.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getTags() != null) {
            client.setTags(request.getTags());
        }
        if (request.getFavoriteServices() != null) {
            client.setFavoriteServices(request.getFavoriteServices());
        }
        
        client.setLastModifiedBy(currentUserId);
        
        Client updatedClient = clientRepository.save(client);
        return convertToDto(updatedClient);
    }
    
    /**
     * Delete a client
     */
    public void deleteClient(Long businessId, Long clientId) {
        Client client = clientRepository.findByBusinessIdAndId(businessId, clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        
        clientRepository.delete(client);
    }
    
    /**
     * Get client count for dashboard
     */
    public long getClientCount(Long businessId) {
        return clientRepository.countByBusinessId(businessId);
    }
    
    /**
     * Get active client count for dashboard
     */
    public long getActiveClientCount(Long businessId) {
        return clientRepository.countByBusinessIdAndStatus(businessId, ClientStatus.ACTIVE);
    }
    
    /**
     * Convert entity to DTO
     */
    private ClientDto convertToDto(Client client) {
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setNotes(client.getNotes());
        dto.setStatus(client.getStatus().getValue());
        dto.setTotalSpent(client.getTotalSpent());
        dto.setTotalAppointments(client.getTotalAppointments());
        dto.setLastVisit(client.getLastVisit());
        dto.setNextAppointment(client.getNextAppointment());
        dto.setTags(client.getTags());
        dto.setFavoriteServices(client.getFavoriteServices());
        dto.setAvatar(client.getAvatar());
        dto.setDateOfBirth(client.getDateOfBirth());
        dto.setJoinDate(client.getJoinDate());
        dto.setCreatedAt(client.getCreatedAt());
        return dto;
    }
}