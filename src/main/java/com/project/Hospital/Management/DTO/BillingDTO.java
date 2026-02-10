package com.project.Hospital.Management.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BillingDTO {
    private Long billId;
    private Long patientId;
    private String patientname;
    private LocalDateTime billDate;
    private BigDecimal roomCharges;
    private BigDecimal medicineCharges;
    private BigDecimal doctorCharges;
    private BigDecimal totalCharges;
    private Boolean status;

    public BillingDTO() {}

    public Long getBillId() { return billId; }
    public void setBillId(Long billId) { this.billId = billId; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public String getPatientname() { return patientname; }
    public void setPatientname(String patientname) { this.patientname = patientname; }
    public LocalDateTime getBillDate() { return billDate; }
    public void setBillDate(LocalDateTime billDate) { this.billDate = billDate; }
    public BigDecimal getRoomCharges() { return roomCharges; }
    public void setRoomCharges(BigDecimal roomCharges) { this.roomCharges = roomCharges; }
    public BigDecimal getMedicineCharges() { return medicineCharges; }
    public void setMedicineCharges(BigDecimal medicineCharges) { this.medicineCharges = medicineCharges; }
    public BigDecimal getDoctorCharges() { return doctorCharges; }
    public void setDoctorCharges(BigDecimal doctorCharges) { this.doctorCharges = doctorCharges; }
    public BigDecimal getTotalCharges() { return totalCharges; }
    public void setTotalCharges(BigDecimal totalCharges) { this.totalCharges = totalCharges; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}
