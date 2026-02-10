package com.project.Hospital.Management.DTO;

import com.project.Hospital.Management.Entities.Doctor;
import com.project.Hospital.Management.Entities.Patient;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class recordDTO {
    private Long recordId;
    private String patientname;
    private Long patientId;
    private String doctorname;
    private Long doctorId;
    private String diagnosis;
    private String prescription;
    private String treatment;
    private LocalDateTime record_date;

    //Constructor


    public recordDTO(Long recordId, String patientname, Long patientId, String doctorname,
                     Long doctorId, String diagnosis, String prescription, String treatment, LocalDateTime record_date) {
        this.recordId = recordId;
        this.patientname = patientname;
        this.patientId = patientId;
        this.doctorname = doctorname;
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.treatment = treatment;
        this.record_date = record_date;
    }
    public recordDTO(){}

    //Getters and Setters

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public LocalDateTime getRecord_date() {
        return record_date;
    }

    public void setRecord_date(LocalDateTime record_date) {
        this.record_date = record_date;
    }
}
