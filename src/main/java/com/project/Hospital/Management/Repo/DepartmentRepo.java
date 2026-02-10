package com.project.Hospital.Management.Repo;

import com.project.Hospital.Management.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Optional<Department> findByDeptId(Long deptId);
    List<Department> findByDeptName(String deptName);
    boolean existsByDeptNameIgnoreCase(String deptName);
    Optional<Department> findByDeptNameIgnoreCase(String deptName);
    List<Department> findByDeptHead(String deptHead);
    List<Department> findByLocation(String location);
}
