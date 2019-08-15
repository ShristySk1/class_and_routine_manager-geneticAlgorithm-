package com.shristy.web.projectmanagement.entity.swarina;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "studentt")


public class Studentt implements Serializable,Comparable<Studentt> {

    
 private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
    private Long id;
//    @Column(name="student_name")
//    private String studentname;
//    @Column(name="student_address")
//    private String address;
    @Column(name="student_email")
    private String email;
//    @Column (name="student_contact")
//    private Long contact;
    @Column (name="student_rollno")
    private Long rollno;
    @Column (name="student_course")
    private String course;
    @Column (name="student_room")
    private String roomno;

    public Studentt() {
    }

    public Studentt(Long id, String email, Long rollno, String course, String roomno) {
        this.id = id;
        this.email = email;
        this.rollno = rollno;
        this.course = course;
        this.roomno = roomno;
    }

    
    
    

//    public Student(Long id, String studentname, String address, String email, Long contact, Long rollno, Long batch, String roomno) {
//        this.id = id;
//        //this.studentname = studentname;
//        this.address = address;
//        this.email = email;
//        this.contact = contact;
//        this.rollno = rollno;
//        this.batch = batch;
//        this.roomno = roomno;
//    }
//    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

   

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    public Long getRollno() {
        return rollno;
    }

    public void setRollno(Long rollno) {
        this.rollno = rollno;
    }

   

//    public String getRoomno() {
//        return roomno;
//    }
//
//    public void setRoomno(String roomno) {
//        this.roomno = roomno;
//    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", email=" + email + ", rollno=" + rollno + ", course=" + course + ", roomno=" + roomno + '}';
    }

    
   
    
    

    @Override
    public int compareTo(Studentt o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
