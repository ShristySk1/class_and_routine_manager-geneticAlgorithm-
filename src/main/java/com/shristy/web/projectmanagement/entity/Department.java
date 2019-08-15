/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "department")
//@NamedQueries({
//    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")})
public class Department implements java.io.Serializable {

    @Size(max = 2147483647)
    @Column(name = "department_type")
    private String departmentType;
    @OneToMany(mappedBy = "departmentId")
    private List<Room> roomList;

    @OneToMany(mappedBy = "departmentId")
    private List<Subjects> subjectsList;

 
    @Column(name = "department_name")
    private String departmentName;

    private static final long serialVersionUID = 1L;
    @Id

    @NotNull
    @Column(name = "department_id")
    private Long departmentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departmentId")
    private List<Courses> coursesList;

    public Department() {
    }

    public Department(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Department(Long departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    @Override
    public String toString() {
        return "Department{" + "departmentId=" + departmentId + '}';
    }

    public List<Subjects> getSubjectsList() {
        return subjectsList;
    }

    public void setSubjectsList(List<Subjects> subjectsList) {
        this.subjectsList = subjectsList;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

  
    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    
  

  

  
    
}
