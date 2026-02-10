
package com.project.Hospital.Management.Repo;

import com.project.Hospital.Management.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    List<Doctor> findByDoctorId(Long doctorId);
    List<Doctor> findByDepartment_DeptId(Long deptId);
    List<Doctor> findBySpecializationIgnoreCase(String specialization);
    List<Doctor> findBySpecializationContainingIgnoreCase(String specialization);
    List<Doctor> findByAvailability(Boolean availability);
    List<Doctor> findByName(String name);
    List<Doctor> findByDepartment_DeptName(String deptName);

    boolean existsByEmailId(String email_id);

    boolean existsByContactNo(String contact_no);
}
