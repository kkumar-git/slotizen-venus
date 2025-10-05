package com.slotizen.venus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.slotizen.venus.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    List<Department> findByBusinessIdOrderByCreatedAtDesc(Long businessId);
    
    List<Department> findByBusinessIdAndIsActiveOrderByCreatedAtDesc(Long businessId, Boolean isActive);
    
    Optional<Department> findByIdAndBusinessId(Long id, Long businessId);
    
    boolean existsByBusinessIdAndName(Long businessId, String name);
    
    boolean existsByBusinessIdAndNameAndIdNot(Long businessId, String name, Long id);
    
    @Query("SELECT COUNT(s) FROM StaffMember s WHERE s.departmentId = :departmentId")
    long countStaffByDepartmentId(@Param("departmentId") Long departmentId);
    
    @Query("SELECT COUNT(s) FROM ServiceEntity s WHERE s.departmentId = :departmentId")
    long countServicesByDepartmentId(@Param("departmentId") Long departmentId);
    
    @Query("SELECT d, " +
           "(SELECT COUNT(sm) FROM StaffMember sm WHERE sm.departmentId = d.id) as staffCount, " +
           "(SELECT COUNT(se) FROM ServiceEntity se WHERE se.departmentId = d.id) as servicesCount " +
           "FROM Department d WHERE d.businessId = :businessId ORDER BY d.createdAt DESC")
    List<Object[]> findDepartmentsWithCounts(@Param("businessId") Long businessId);
    
    long countByBusinessIdAndIsActive(Long businessId, Boolean isActive);
}