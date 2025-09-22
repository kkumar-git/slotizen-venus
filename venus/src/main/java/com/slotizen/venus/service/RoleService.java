package com.slotizen.venus.service;

import com.slotizen.venus.dto.RoleAssignRequest;
import java.util.List;

public interface RoleService {
    List<String> getAllRoles();
    void assignRolesToUser(Long userId, RoleAssignRequest request);
    List<String> getUserRoles(Long userId);
    void removeUserRole(Long userId, Long roleId);
}
