package com.slotizen.venus.controller;

import com.slotizen.venus.dto.RoleAssignRequest;
import com.slotizen.venus.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<?> getAllRoles() {
        // ...implementation...
        return ResponseEntity.ok().build();
    }
}
