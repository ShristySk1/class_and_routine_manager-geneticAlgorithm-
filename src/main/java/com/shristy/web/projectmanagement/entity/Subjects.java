/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "subject")
@NamedQueries({
    @NamedQuery(name = "Subjects.findAll", query = "SELECT s FROM Subjects s")})
public class Subjects implements Serializable,Comparable<Subjects> {
     
    @Id
    @NotNull
    @Column(name = "subject_id")
    private Long subjectId;
   
    @NotNull
   
    @Column(name = "subject_name")
    private String subjectName;
   
    @NotNull
   
    @Column(name = "subject_code")
    private String subjectCode;
 

   // private static final long serialVersionUID = 1L;

    
    @Column(name = "period_per_week")
    private Integer periodPerWeek;
    @Column(name = "is_lab")
    private Boolean isLab;
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @ManyToOne(optional = false)
    private Courses courseId;
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    @ManyToOne
    private Department departmentId;

    public Subjects(Long subjectId, String subjectName, String subjectCode, Courses courseId) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.subjectId = subjectId;
        this.courseId = courseId;
    }

    public Subjects() {
    }

    public Subjects(Long subjectId,String subjectName, String subjectCode, Integer periodPerWeek, Boolean isLab, Courses courseId, Department departmentId) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.subjectId = subjectId;
        this.periodPerWeek = periodPerWeek;
        this.isLab = isLab;
        this.courseId = courseId;
        this.departmentId = departmentId;
    }

   
    
    

    public Subjects(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Subjects(Long subjectId, String subjectName, String subjectCode) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getPeriodPerWeek() {
        return periodPerWeek;
    }

    public void setPeriodPerWeek(Integer periodPerWeek) {
        this.periodPerWeek = periodPerWeek;
    }

    public Boolean getIsLab() {
        return isLab;
    }

    public void setIsLab(Boolean isLab) {
        this.isLab = isLab;
    }

    public Courses getCourseId() {
        return courseId;
    }

    public void setCourseId(Courses courseId) {
        this.courseId = courseId;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "com.shristy.web.projectmanagement.entity.Subjects[ subjectId=" + subjectId + " ]";
    }

    @Override
    public int compareTo(Subjects o) {
         if (getSubjectId() == null || o.getSubjectId() == null) {
      return 0;
    }
    return getSubjectId().compareTo(o.getSubjectId());
   }

   


}
