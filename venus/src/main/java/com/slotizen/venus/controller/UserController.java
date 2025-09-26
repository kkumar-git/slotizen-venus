package com.slotizen.venus.controller;

import com.slotizen.venus.dto.RoleAssignRequest;
import com.slotizen.venus.service.RoleService;
import com.slotizen.venus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/{userId}/roles")
    public ResponseEntity<?> assignRolesToUser(@PathVariable Long userId, @RequestBody RoleAssignRequest request) {
        // ...implementation...
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/roles")
    public ResponseEntity<?> getUserRoles(@PathVariable Long userId) {
        // ...implementation...
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> removeUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        // ...implementation...
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUserProfile() {
        // ...implementation...
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        // ...implementation...
        return ResponseEntity.ok().build();
    }
}
