package com.project.Hospital.Management.Repo;

import com.project.Hospital.Management.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    List<Patient> findByName(String name);
    List<Patient> findByAge(int age);
    List<Patient> findByDoctor_DoctorId(Long doctorId);
    List<Patient> findByDoctor_NameContainingIgnoreCase(String doctorName);
    List<Patient> findByReleaseDate(LocalDateTime releaseDate);
    List<Patient> findByAdmissionDate(LocalDateTime admissionDate);
    List<Patient> findByBloodGroup(String bloodGroup);
}
