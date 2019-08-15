/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;



import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;


/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "course")

public class Courses implements java.io.Serializable,Comparable<Courses>{

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<Subjects> subjectsList;


    @Id

      @Column(name = "course_id")
    private Long coursesId;


    @Column(name = "course_name")
    private String coursesName;
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    @ManyToOne(optional = false)
   
    private Department departmentId;

    public Courses() {
    }

    public Courses(Long coursesId) {
        this.coursesId = coursesId;
    }

    public Courses(Long coursesId, String coursesName, Department departmentId) {
        this.coursesId = coursesId;
        this.coursesName = coursesName;
        this.departmentId = departmentId;
    }

    public Long getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(Long coursesId) {
        this.coursesId = coursesId;
    }

    public String getCoursesName() {
        return coursesName;
    }

    public void setCoursesName(String coursesName) {
        this.coursesName = coursesName;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }
    
      public List<Subjects> getSubjectsList() {
        return subjectsList;
    }

    public void setSubjectsList(List<Subjects> subjectsList) {
        this.subjectsList = subjectsList;
    }

    @Override
    public String toString() {
        return "Courses1{" + "coursesId=" + coursesId + ", coursesName=" + coursesName + ", departmentId=" + departmentId + '}';
    }

   
    

    @Override
    public int compareTo(Courses o) {
    if (getCoursesId() == null || o.getCoursesId() == null) {
      return 0;
    }
    return getCoursesId().compareTo(o.getCoursesId());
  }


}
