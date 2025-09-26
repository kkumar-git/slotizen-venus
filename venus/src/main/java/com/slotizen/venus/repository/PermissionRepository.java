package com.slotizen.venus.repository;

import java.util.Optional;

import com.slotizen.venus.model.Permission;

public interface PermissionRepository {
	Optional<Permission> findByName(String name);
}
