package com.project.Hospital.Management.DTO;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class patientDTO {
    private Long patientId;
    private String name;
    private int age ;
    private String gender;
    private String contact_no;
    private String address;
    private String email_id;
    private LocalDateTime release_date;
    private LocalDateTime admission_date;
    private String blood_group;
    private String doctorname;
    private Long doctorID;

    //Constructor
    public patientDTO(){}

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public LocalDateTime getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDateTime release_date) {
        this.release_date = release_date;
    }

    public LocalDateTime getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(LocalDateTime admission_date) {
        this.admission_date = admission_date;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    public patientDTO(Long patientId, String name, int age, String gender, String contact_no, String address, String email_id, LocalDateTime release_date,
                      LocalDateTime admission_date, String blood_group, String doctorname, Long doctorID) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact_no = contact_no;
        this.address = address;
        this.email_id = email_id;
        this.release_date = release_date;
        this.admission_date = admission_date;
        this.blood_group = blood_group;
        this.doctorname = doctorname;
        this.doctorID = doctorID;

    }
}
