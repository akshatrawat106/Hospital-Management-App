package com.project.Hospital.Management.DTO;

import com.project.Hospital.Management.Entities.Doctor;
import com.project.Hospital.Management.Entities.Patient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

public class appointmentDTO {
    private Long appoint_id;
    private Long patientId;
    private String patientname;
    private Long doctorId;
    private String doctorname;
    private LocalDate appointment_date;
    private LocalTime appointment_time;
    private String status;

    //Constructor
    public appointmentDTO(){}

    public appointmentDTO(Long appoint_id, Long patientId, String patientname, Long doctorId,
                          String doctorname, LocalDate appointment_date, LocalTime appointment_time, String status) {
        this.appoint_id = appoint_id;
        this.patientId = patientId;
        this.patientname = patientname;
        this.doctorId = doctorId;
        this.doctorname = doctorname;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.status = status;
    }

    //Getters and Setters

    public Long getAppoint_id() {
        return appoint_id;
    }

    public void setAppoint_id(Long appoint_id) {
        this.appoint_id = appoint_id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public LocalDate getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(LocalDate appointment_date) {
        this.appointment_date = appointment_date;
    }

    public LocalTime getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(LocalTime appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
