package com.slotizen.venus.controller;

import com.slotizen.venus.dto.ApiResponse;
import com.slotizen.venus.dto.ClientDto;
import com.slotizen.venus.dto.CreateClientRequest;
import com.slotizen.venus.dto.UpdateClientRequest;
import com.slotizen.venus.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/businesses/{businessId}/clients")
@PreAuthorize("hasRole('USER')")
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    
    /**
     * GET /api/businesses/{businessId}/clients
     * Get all clients for a business (with optional search and filter)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientDto>>> getClients(
            @PathVariable Long businessId,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status) {
        
        List<ClientDto> clients;
        
        if (search != null && !search.trim().isEmpty()) {
            clients = clientService.searchClients(businessId, search.trim());
        } else if (status != null && !status.trim().isEmpty()) {
            clients = clientService.getClientsByStatus(businessId, status.trim());
        } else {
            clients = clientService.getAllClients(businessId);
        }
        
        return ResponseEntity.ok(new ApiResponse<>(true, "Clients retrieved successfully", clients));
    }
    
    /**
     * GET /api/businesses/{businessId}/clients/{clientId}
     * Get a single client by ID
     */
    @GetMapping("/{clientId}")
    public ResponseEntity<ApiResponse<ClientDto>> getClient(
            @PathVariable Long businessId,
            @PathVariable Long clientId) {
        
        ClientDto client = clientService.getClientById(businessId, clientId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Client retrieved successfully", client));
    }
    
    /**
     * POST /api/businesses/{businessId}/clients
     * Create a new client
     */
    @PostMapping
    public ResponseEntity<ApiResponse<ClientDto>> createClient(
            @PathVariable Long businessId,
            @Valid @RequestBody CreateClientRequest request) {
        
        ClientDto client = clientService.createClient(businessId, request);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Client created successfully", client));
    }
    
    /**
     * PUT /api/businesses/{businessId}/clients/{clientId}
     * Update an existing client
     */
    @PutMapping("/{clientId}")
    public ResponseEntity<ApiResponse<ClientDto>> updateClient(
            @PathVariable Long businessId,
            @PathVariable Long clientId,
            @Valid @RequestBody UpdateClientRequest request) {
        
        ClientDto client = clientService.updateClient(businessId, clientId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Client updated successfully", client));
    }
    
    /**
     * DELETE /api/businesses/{businessId}/clients/{clientId}
     * Delete a client
     */
    @DeleteMapping("/{clientId}")
    public ResponseEntity<ApiResponse<String>> deleteClient(
            @PathVariable Long businessId,
            @PathVariable Long clientId) {
        
        clientService.deleteClient(businessId, clientId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Client deleted successfully", null));
    }
    
    /**
     * GET /api/businesses/{businessId}/clients/count
     * Get client count for dashboard
     */
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> getClientCount(
            @PathVariable Long businessId) {
        
        long count = clientService.getClientCount(businessId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Client count retrieved successfully", count));
    }
    
    /**
     * GET /api/businesses/{businessId}/clients/active-count
     * Get active client count for dashboard
     */
    @GetMapping("/active-count")
    public ResponseEntity<ApiResponse<Long>> getActiveClientCount(
            @PathVariable Long businessId) {
        
        long count = clientService.getActiveClientCount(businessId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Active client count retrieved successfully", count));
    }
}