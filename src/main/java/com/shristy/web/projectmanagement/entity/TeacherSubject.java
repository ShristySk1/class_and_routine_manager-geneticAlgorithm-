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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "teacher_subject")
@NamedQueries({
    @NamedQuery(name = "TeacherSubject.findAll", query = "SELECT t FROM TeacherSubject t")})
public class TeacherSubject implements Serializable,Comparable<TeacherSubject> {

    private static final long serialVersionUID = 1L;
    @Id
   
    
    @Column(name = "ts_id",nullable= false)
    
    private Long tsId;
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    @ManyToOne(optional = false)
    private Subjects subjectId;
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    @ManyToOne(optional = false)
    private Teacher teacherId;

    public TeacherSubject() {
    }

    public TeacherSubject(Long tsId) {
        this.tsId = tsId;
    }

    public TeacherSubject(Long tsId, Subjects subjectId, Teacher teacherId) {
        this.tsId = tsId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }

    public TeacherSubject(Subjects subjectId, Teacher teacherId) {
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }

    public Long getTsId() {
        return tsId;
    }

    public void setTsId(Long tsId) {
        this.tsId = tsId;
    }

    public Subjects getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subjects subjectId) {
        this.subjectId = subjectId;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }


    @Override
    public String toString() {
        return "com.shristy.web.projectmanagement.entity.TeacherSubject[ tsId=" + tsId + " ]";
    }

    @Override
    public int compareTo(TeacherSubject o) {
        if (getTsId() == null || o.getTsId() == null) {
      return 0;
    }
    return getTsId().compareTo(o.getTsId());
   }
    
}
