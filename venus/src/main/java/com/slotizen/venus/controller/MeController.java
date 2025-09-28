package com.slotizen.venus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slotizen.venus.dto.PermissionResponse;
import com.slotizen.venus.service.PermissionService;
import com.slotizen.venus.util.SecurityUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
public class MeController {

    private final PermissionService permissionService;

    @GetMapping("/permissions")
    public PermissionResponse getMyPermissions() {
        String email = SecurityUtils.getCurrentEmail();
        return new PermissionResponse(permissionService.getPermissionsForUser(email));
    }
}