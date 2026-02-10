package com.project.Hospital.Management.Repo;

import com.project.Hospital.Management.Entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BillingRepo extends JpaRepository<Billing, Long> {

    List<Billing> findByPatient_PatientId(Long patientId);

    List<Billing> findByPatientname(String patientname);

    List<Billing> findByPatient_Name(String name);

    List<Billing> findByStatus(Boolean status);
}
