package com.shristy.web.projectmanagement.entity.swarina;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class Studentss {
    
    private String email;
    private Long rollno;

    public Studentss() {
    }

    public Studentss(String email, Long rollno) {
        this.email = email;
        this.rollno = rollno;
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
    
    
    
}
