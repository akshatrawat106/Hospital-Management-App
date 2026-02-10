package com.project.Hospital.Management.Repo;

import com.project.Hospital.Management.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatient_PatientId(Long patientId);
    List<Appointment> findByDoctor_DoctorId(Long doctorId);
    List<Appointment> findByAppointId(Long appointId);
    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);
    List<Appointment> findByAppointmentTime(LocalTime appointmentTime);

    boolean existsByDoctor_DoctorIdAndAppointmentDateAndAppointmentTime(Long doctorId, LocalDate appointmentDate, LocalTime appointmentTime);
}
