package com.project.Hospital.Management.DTO;

import jakarta.persistence.Column;

public class departmentDTO {
    private Long dept_id;
    private String dept_name;
    private String location;
    private String dept_head;

    //Constructor
    public departmentDTO(){}

    public departmentDTO(Long dept_id, String dept_name, String location, String dept_head) {
        this.dept_id = dept_id;
        this.dept_name = dept_name;
        this.location = location;
        this.dept_head = dept_head;
    }

    //Getters and Setters

    public Long getDept_id() {
        return dept_id;
    }

    public void setDept_id(Long dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDept_head() {
        return dept_head;
    }

    public void setDept_head(String dept_head) {
        this.dept_head = dept_head;
    }
}
