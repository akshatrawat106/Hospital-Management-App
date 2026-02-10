package com.project.Hospital.Management.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @Column(name = "prescription", nullable = false)
    private String prescription;

    @Column(name = "treatment", nullable = false)
    private String treatment;

    @Column(name = "record_date", nullable = false)
    private LocalDateTime recordDate;

    public Record() {}

    public Record(Long recordId, Patient patient, Doctor doctor, String diagnosis,
                  String prescription, String treatment, LocalDateTime recordDate) {
        this.recordId = recordId;
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.treatment = treatment;
        this.recordDate = recordDate;
    }

    public Long getRecordId() { return recordId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getDiagnosis() { return diagnosis; }
    public String getPrescription() { return prescription; }
    public String getTreatment() { return treatment; }
    public LocalDateTime getRecordDate() { return recordDate; }

    public void setRecordId(Long recordId) { this.recordId = recordId; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public void setPrescription(String prescription) { this.prescription = prescription; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    public void setRecordDate(LocalDateTime recordDate) { this.recordDate = recordDate; }
}
