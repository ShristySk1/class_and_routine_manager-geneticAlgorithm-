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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "\"teachertable\"")

public class Teachertable implements Serializable,Comparable<Teachertable> {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name = "\"id\"")
    private Long id;
     @Column(name = "\"tid\"")
    private Long tid;
    
    @Column(name = "\"time\"")
    private String time;
  
    
   
    @Column(name = "\"days\"")
    private String days;
    
    @Column(name = "\"roomno\"")
    private String roomno;

  

    public Teachertable(Long id, Long tid, String time, String days, String roomno) {
        this.id = id;
        this.tid = tid;
        this.time = time;
      
        this.days = days;
        this.roomno = roomno;
    }
    

    public Teachertable() {
    }

    public Teachertable(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

   

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    

    public Long getTid() {
        return tid;
    }

    @Override
    public String toString() {
        return "Teachertable{" + "id=" + id + ", tid=" + tid + ", time=" + time + ", days=" + days + ", roomno=" + roomno + '}';
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    
      @Override
    public int compareTo(Teachertable o) {
         if (getId() == null || o.getId() == null) {
      return 0;
    }
    return getId().compareTo(o.getId());
   }
    
}
