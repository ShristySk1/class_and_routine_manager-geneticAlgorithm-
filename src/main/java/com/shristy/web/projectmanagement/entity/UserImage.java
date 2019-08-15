/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

/**
 *
 * @author Administrator
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
 
@Entity
@Table(name = "\"userImage\"")
public class UserImage implements Serializable,Comparable<UserImage> {
    
    
     
    @Id
    //@NotNull
    @GeneratedValue
    @Column(name = "\"imgId\"", nullable = false)
    private Long imgId;
    
     @Column(name = "\"image\"", nullable = true)
    private String image;
 
    
     @JoinColumn(name = "\"userId\"",nullable= true)
    @OneToOne
   
    private AppUser user;
     
     

    public UserImage() {
    }

    public UserImage(Long imgId, String image, AppUser user) {
        this.imgId = imgId;
        this.image = image;
        this.user = user;
    }

   

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
       
       

  
    @Override
    public int compareTo(UserImage o) {
         if (getImgId() == null || o.getImgId() == null) {
      return 0;
    }
    return getImgId().compareTo(o.getImgId());
    }
 
    
     
}