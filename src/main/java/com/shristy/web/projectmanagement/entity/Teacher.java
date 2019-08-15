/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "teacher")
@NamedQueries({
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t")})
public class Teacher implements Serializable,Comparable<Teacher> {

    private static final long serialVersionUID = 1L;
    @Id
   
    @NotNull
    @Column(name = "teacher_id")
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long teacherId;
   
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "teacher_name")
    private String teacherName;
   
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "teacher_email")
    private String teacherEmail;
    @Column(name = "teacher_contact")
    private BigInteger teacherContact;
   
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "teacher_type")
    private String teacherType;
    @Column(name = "teacher_workinghr")
    @Temporal(TemporalType.TIME)
    private Date teacherWorkinghr;
   
    @NotNull
    @Column(name = "status")
    private boolean status;
    @Size(max = 2147483647)
    @Column(name = "teacher_picture")
    private String teacherPicture;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacherId")
    private List<TeacherSubject> teacherSubjectList;
    
     @JoinColumn(name = "\"user_id\"",nullable=true)
    @OneToOne
   
    private AppUser user;

    public Teacher() {
    }

    public Teacher(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Teacher(Long teacherId, String teacherName, String teacherEmail,BigInteger teacherContact, String teacherType, boolean status) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherContact= teacherContact;
        this.teacherType = teacherType;
        this.status = status;
    }
    
     public Teacher(Long teacherId, String teacherName, String teacherEmail,BigInteger teacherContact, String teacherType, boolean status,String teacherPicture,AppUser user) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherContact= teacherContact;
        this.teacherType = teacherType;
        this.status = status;
        this.teacherPicture= teacherPicture;
        this.user=user;
    }

    /**
     *
     * @param teacherId
     * @param teacherName
     * @param teacherEmail
     * @param teacherType
     * @param status
     */
    public Teacher(Long teacherId, String teacherName, String teacherEmail, String teacherType, boolean status) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherType = teacherType;
        this.status = status;
    }

    public Teacher(String teacher_name, String teacher_email, BigInteger teacher_contact, String teacher_type, Boolean status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
      public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public BigInteger getTeacherContact() {
        return teacherContact;
    }

    public void setTeacherContact(BigInteger teacherContact) {
        this.teacherContact = teacherContact;
    }

    public String getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(String teacherType) {
        this.teacherType = teacherType;
    }

    public Date getTeacherWorkinghr() {
        return teacherWorkinghr;
    }

    public void setTeacherWorkinghr(Date teacherWorkinghr) {
        this.teacherWorkinghr = teacherWorkinghr;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTeacherPicture() {
        return teacherPicture;
    }

    public void setTeacherPicture(String teacherPicture) {
        this.teacherPicture = teacherPicture;
    }

    public List<TeacherSubject> getTeacherSubjectList() {
        return teacherSubjectList;
    }

    public void setTeacherSubjectList(List<TeacherSubject> teacherSubjectList) {
        this.teacherSubjectList = teacherSubjectList;
    }

    
    @Override
    public String toString() {
        return "com.shristy.web.projectmanagement.entity.Teacher[ teacherId=" + teacherId + " ]";
    }
        @Override
    public int compareTo(Teacher o) {
    if (getTeacherId() == null || o.getTeacherId() == null) {
      return 0;
    }
    return getTeacherId().compareTo(o.getTeacherId());
  }

    
}
