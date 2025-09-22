package com.slotizen.venus.service.impl;

import com.slotizen.venus.dto.RoleAssignRequest;
import com.slotizen.venus.service.RoleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<String> getAllRoles() {
        // TODO: Implement get all roles logic
        return null;
    }

    @Override
    public void assignRolesToUser(Long userId, RoleAssignRequest request) {
        // TODO: Implement assign roles to user logic
    }

    @Override
    public List<String> getUserRoles(Long userId) {
        // TODO: Implement get user roles logic
        return null;
    }

    @Override
    public void removeUserRole(Long userId, Long roleId) {
        // TODO: Implement remove user role logic
    }
}
