package com.slotizen.venus.controller;

import com.slotizen.venus.dto.PermissionResponse;
import com.slotizen.venus.dto.RoleAssignRequest;
import com.slotizen.venus.dto.UserBusinessDto;
import com.slotizen.venus.service.PermissionService;
import com.slotizen.venus.service.RoleService;
import com.slotizen.venus.service.UserService;
import com.slotizen.venus.util.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

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

    @GetMapping("/permissions")
    public PermissionResponse getMyPermissions() {
        String email = SecurityUtils.getCurrentEmail();
        return new PermissionResponse(permissionService.getPermissionsForUser(email));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        // ...implementation...
        return ResponseEntity.ok().build();
    }
    
    /**
     * Get all UserBusiness relationships for the current user
     */
    @GetMapping("/businesses")
    public ResponseEntity<List<UserBusinessDto>> getUserBusinesses() {
        try {
            List<UserBusinessDto> userBusinesses = userService.getUserBusinesses();
            return ResponseEntity.ok(userBusinesses);
        } catch (Exception e) {
            e.printStackTrace(); // Add logging to see the actual error
            return ResponseEntity.status(500).build();
        }
    }
}
