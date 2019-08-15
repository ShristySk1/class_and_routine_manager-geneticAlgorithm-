/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

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
@Table(name = "feedback")
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")})
public class Feedback implements Serializable,Comparable<Feedback>{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "id")
    private Long id;
    @Column(name="email")
    private String email;
    @Column(name="feed")
    private String feed;

    public Feedback() {
    }
    
    

    public Feedback(Long id, String email, String feed) {
        this.id = id;
        this.email = email;
        this.feed = feed;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", email=" + email + ", feed=" + feed + '}';
    }

    @Override
    public int compareTo(Feedback o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (getId() == null || o.getId() == null) {
      return 0;
    }
    return getId().compareTo(o.getId());
    }
    
    
    
}
