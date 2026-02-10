package com.project.Hospital.Management.Repo;

import com.project.Hospital.Management.Entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepo extends JpaRepository<Record, Long> {
    List<Record> findByPatient_PatientId(Long patientId);
    List<Record> findByDoctor_DoctorId(Long doctorId);
    List<Record> findByRecordDate(LocalDateTime recordDate);
}
