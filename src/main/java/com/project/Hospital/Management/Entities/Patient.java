package com.project.Hospital.Management.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "contact_no", nullable = false)
    private String contactNo;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Column(name = "admission_date")
    private LocalDateTime admissionDate;

    @Column(name = "blood_group", nullable = false)
    private String bloodGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    public Patient() {}

    public Patient(Long patientId, String name, int age, String gender,
                   String contactNo, String address, String emailId,
                   LocalDateTime releaseDate, LocalDateTime admissionDate, String bloodGroup,
                   Doctor doctor) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactNo = contactNo;
        this.address = address;
        this.emailId = emailId;
        this.releaseDate = releaseDate;
        this.admissionDate = admissionDate;
        this.bloodGroup = bloodGroup;
        this.doctor = doctor;
    }

    public Long getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getContactNo() { return contactNo; }
    public String getAddress() { return address; }
    public String getEmailId() { return emailId; }
    public LocalDateTime getReleaseDate() { return releaseDate; }
    public LocalDateTime getAdmissionDate() { return admissionDate; }
    public String getBloodGroup() { return bloodGroup; }
    public Doctor getDoctor() { return doctor; }

    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    public void setAddress(String address) { this.address = address; }
    public void setEmailId(String emailId) { this.emailId = emailId; }
    public void setReleaseDate(LocalDateTime releaseDate) { this.releaseDate = releaseDate; }
    public void setAdmissionDate(LocalDateTime admissionDate) { this.admissionDate = admissionDate; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}
