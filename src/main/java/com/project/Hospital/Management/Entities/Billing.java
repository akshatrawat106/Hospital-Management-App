package com.project.Hospital.Management.Entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "billing")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    @Column(name = "patientname", nullable = false)
    private String patientname; // snapshot

    @Column(name = "bill_date", nullable = false)
    private LocalDateTime billDate;

    @Column(name = "room_charges")
    private BigDecimal roomCharges;

    @Column(name = "medicine_charges", nullable = false)
    private BigDecimal medicineCharges;

    @Column(name = "doctor_charges", nullable = false)
    private BigDecimal doctorCharges;

    @Column(name = "total_charges", nullable = false)
    private BigDecimal totalCharges;

    @Column(name = "status", nullable = false)
    private Boolean status;

    public Billing() {}

    public Long getBillId() { return billId; }
    public Patient getPatient() { return patient; }
    public String getPatientname() { return patientname; }
    public LocalDateTime getBillDate() { return billDate; }
    public BigDecimal getRoomCharges() { return roomCharges; }
    public BigDecimal getMedicineCharges() { return medicineCharges; }
    public BigDecimal getDoctorCharges() { return doctorCharges; }
    public BigDecimal getTotalCharges() { return totalCharges; }
    public Boolean getStatus() { return status; }

    public void setBillId(Long billId) { this.billId = billId; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public void setPatientname(String patientname) { this.patientname = patientname; }
    public void setBillDate(LocalDateTime billDate) { this.billDate = billDate; }
    public void setRoomCharges(BigDecimal roomCharges) { this.roomCharges = roomCharges; }
    public void setMedicineCharges(BigDecimal medicineCharges) { this.medicineCharges = medicineCharges; }
    public void setDoctorCharges(BigDecimal doctorCharges) { this.doctorCharges = doctorCharges; }
    public void setTotalCharges(BigDecimal totalCharges) { this.totalCharges = totalCharges; }
    public void setStatus(Boolean status) { this.status = status; }
}
