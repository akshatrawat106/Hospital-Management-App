package com.project.Hospital.Management.DTO;

import com.project.Hospital.Management.Entities.Department;

public class doctorDTO {
    private Long doctorId;
    private String name;
    private String specialization;
    private String contact_no;
    private String email_id;
    private Long departmentId;
    private Boolean availability;

    //constructor
    public doctorDTO(){}

    public doctorDTO(Long doctorId, String name, String specialization,
                     String contact_no, String email_id, Long departmentId, Boolean availability) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.contact_no = contact_no;
        this.email_id = email_id;
        this.departmentId = departmentId;
        this.availability = availability;
    }

    //Getter

    public Long getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Boolean getAvailability() {
        return availability;
    }

    //setters

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
