/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
 
@Entity
@Table(name = "App_User", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name") })
public class AppUser implements Serializable,Comparable<AppUser> {
 
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "User_Id", nullable = false)
    private Long userId;
 
    @Column(name = "User_Name", length = 36, nullable = false)
    private String userName;
    
    @Column(name = "Full_Name")
    private String fullName;
    
     @Column(name = "\"Email\"")
    private String email;
 
    @Column(name = "Encrypted_Password", length = 100, nullable = false)
    private String encrytedPassword;
  
     @Column(name = "Enabled", length = 1, nullable = false)
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
   
    
    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "app_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<AppRole> roles;

    public AppUser() {
    }

    public AppUser(Long userId, String userName, String fullName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
    }

    public AppUser(Long userId, String userName, String fullName, String email, String encrytedPassword, boolean enabled) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.encrytedPassword = encrytedPassword;
        this.enabled = enabled;
    }

    public AppUser(Long userId, String userName, String fullName, String email, String encrytedPassword, boolean enabled, Set<AppRole> roles) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.encrytedPassword = encrytedPassword;
        this.enabled = enabled;
        this.roles = roles;
    }
 
   
    
    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getEncrytedPassword() {
        return encrytedPassword;
    }
 
    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
  

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public int compareTo(AppUser o) {
         if (getUserId() == null || o.getUserId() == null) {
      return 0;
    }
    return getUserId().compareTo(o.getUserId());
    }
    
    
 
}