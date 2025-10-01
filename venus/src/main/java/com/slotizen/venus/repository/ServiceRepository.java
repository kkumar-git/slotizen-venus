package com.slotizen.venus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slotizen.venus.model.ServiceEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, String> {
    List<ServiceEntity> findByBusinessId(String businessId);
    void deleteByBusinessId(String businessId);
}