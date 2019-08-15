
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "\"Student\"")

public class Student implements Serializable,Comparable<Student> {

    private static final long serialVersionUID = 1L;
    @Id
   
    
    @Column(name = "\"student_id\"",nullable=false)
     @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long studentId;
   
    
   
    @Column(name = "\"student_name\"",nullable=false)
    private String studentName;
   
  
    
    @Column(name = "\"student_email\"",nullable=false)
    private String studentEmail;
    @Column(name = "\"student_rollno\"")
    private Integer rollno;
   @Column(name = "\"student_contact\"")
    private BigInteger stdcon;
   @Column(name = "\"student_address\"")
    private String stdadd;
    @Column(name = "\"student_batch\"")
    private Integer stdbatch;
     @Column(name = "\"section\"")
    private String section;
      
    
     @JoinColumn(name = "\"user_id\"",nullable=true)
    @OneToOne
   
    private AppUser user;
     
       @JoinColumn(name = "\"course_id\"",referencedColumnName = "course_id")
    @OneToOne
   
    private Courses course;
       
        
   
 
   
  
   
  
   
    
    public Student() {
    }

    public Student(Long studentId, String studentName, String studentEmail, Integer rollno, BigInteger stdcon, String stdadd, Integer stdbatch, AppUser user) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.rollno = rollno;
        this.stdcon = stdcon;
        this.stdadd = stdadd;
        this.stdbatch = stdbatch;
        this.user = user;
       
    }

    
    
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Integer getRollno() {
        return rollno;
    }

    public void setRollno(Integer rollno) {
        this.rollno = rollno;
    }

    public BigInteger getStdcon() {
        return stdcon;
    }

    public void setStdcon(BigInteger stdcon) {
        this.stdcon = stdcon;
    }

    public String getStdadd() {
        return stdadd;
    }

    public void setStdadd(String stdadd) {
        this.stdadd = stdadd;
    }

    public Integer getStdbatch() {
        return stdbatch;
    }

    public void setStdbatch(Integer stdbatch) {
        this.stdbatch = stdbatch;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

   

    @Override
    public String toString() {
        return "Student{" + "studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail + ", rollno=" + rollno + ", stdcon=" + stdcon + ", stdadd=" + stdadd + ", stdbatch=" + stdbatch + ", user=" + user + ", course=" + course + ", sec=" + section + '}';
    }

   

   
    
    
        @Override
    public int compareTo(Student o) {
    if (getStudentId() == null || o.getStudentId() == null) {
      return 0;
    }
    return getStudentId().compareTo(o.getStudentId());
  }

    
}
