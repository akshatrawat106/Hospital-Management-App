package com.project.Hospital.Management.Service;

import com.project.Hospital.Management.DTO.appointmentDTO;
import com.project.Hospital.Management.Entities.Appointment;
import com.project.Hospital.Management.Entities.Doctor;
import com.project.Hospital.Management.Entities.Patient;
import com.project.Hospital.Management.Repo.AppointmentRepo;
import com.project.Hospital.Management.Repo.DoctorRepo;
import com.project.Hospital.Management.Repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientRepo patientRepo;

    public appointmentDTO takeAppointment(Long doctorId, Long patientId,
                                           LocalDate appointmentDate, LocalTime appointmentTime)
    {
        Doctor doctor = doctorRepo.findById(doctorId).orElseThrow(()-> new RuntimeException("Doctor Not Found"));
        Patient patient = patientRepo.findById(patientId).orElseThrow(()-> new RuntimeException("Patient Not Found"));

        if(!doctor.getAvailability())
        {
            throw new RuntimeException("Doctor is not available");
        }
        boolean slotTaken = appointmentRepo
                .existsByDoctor_DoctorIdAndAppointmentDateAndAppointmentTime(doctorId, appointmentDate, appointmentTime);
        if(slotTaken)
        {
            throw new RuntimeException("Slot is already taken");
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setAppointmentTime(appointmentTime);
        appointment.setStatus("BOOKED");

        appointmentRepo.save(appointment);

        appointmentDTO dto = new appointmentDTO();
        dto.setAppoint_id(appointment.getAppointId());
        dto.setDoctorId(doctorId);
        dto.setPatientId(patientId);
        dto.setPatientname(patient.getName());
        dto.setDoctorname(doctor.getName());
        dto.setAppointment_date(appointmentDate);
        dto.setAppointment_time(appointmentTime);
        dto.setStatus(appointment.getStatus());

        return dto;

    }
}
