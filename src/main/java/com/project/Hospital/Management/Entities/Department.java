package com.project.Hospital.Management.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    @Column(name = "dept_name",nullable = false,unique = true)
    private String deptName;
    @Column(name = "location",nullable = false)
    private String location;
    @Column(name = "dept_head",nullable = false)
    private String deptHead;

    //Constructor

    public Department(){}

    public Department(Long deptId, String deptName, String location, String deptHead) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.location = location;
        this.deptHead = deptHead;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeptHead() {
        return deptHead;
    }

    public void setDeptHead(String deptHead) {
        this.deptHead = deptHead;
    }
}
