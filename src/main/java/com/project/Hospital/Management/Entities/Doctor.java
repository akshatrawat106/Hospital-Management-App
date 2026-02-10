package com.project.Hospital.Management.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @Column(name = "contact_no", nullable = false, unique = true)
    private String contactNo;

    @Column(name = "email_id", nullable = false,unique = true)
    private String emailId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "availability", nullable = false)
    private Boolean availability;

    public Doctor() {}

    public Doctor(Long doctorId, String name, String specialization,
                  String contactNo, String emailId, Department department,
                  Boolean availability) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.contactNo = contactNo;
        this.emailId = emailId;
        this.department = department;
        this.availability = availability;
    }

    public Long getDoctorId() { return doctorId; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public String getContactNo() { return contactNo; }
    public String getEmailId() { return emailId; }
    public Department getDepartment() { return department; }
    public Boolean getAvailability() { return availability; }

    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public void setName(String name) { this.name = name; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    public void setEmailId(String emailId) { this.emailId = emailId; }
    public void setDepartment(Department department) { this.department = department; }
    public void setAvailability(Boolean availability) { this.availability = availability; }
}
