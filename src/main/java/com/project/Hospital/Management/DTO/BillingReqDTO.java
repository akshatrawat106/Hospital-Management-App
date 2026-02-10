
package com.project.Hospital.Management.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BillingReqDTO {
    private Long patientId;

    private LocalDateTime billDate;
    private BigDecimal roomCharges;
    private BigDecimal medicineCharges;
    private BigDecimal doctorCharges;

    public BillingReqDTO() {}

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public LocalDateTime getBillDate() { return billDate; }
    public void setBillDate(LocalDateTime billDate) { this.billDate = billDate; }
    public BigDecimal getRoomCharges() { return roomCharges; }
    public void setRoomCharges(BigDecimal roomCharges) { this.roomCharges = roomCharges; }
    public BigDecimal getMedicineCharges() { return medicineCharges; }
    public void setMedicineCharges(BigDecimal medicineCharges) { this.medicineCharges = medicineCharges; }
    public BigDecimal getDoctorCharges() { return doctorCharges; }
    public void setDoctorCharges(BigDecimal doctorCharges) { this.doctorCharges = doctorCharges; }
}
