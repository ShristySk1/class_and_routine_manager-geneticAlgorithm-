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
import javax.persistence.Table;

/**
 *
 * @author Rohini Shrestha
 */
@Entity

@Table(name = "App_User_Role")
public class AppUserRole implements Serializable,Comparable<AppUserRole> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
   
    @Column(name = "User_Role_Id", nullable = false)
    private Long userRoleId;
 
    @Column(name = "User_Id", nullable = false)
    private Long userId;
 
    @Column(name = "Role_Id", nullable = false)
    private Long roleId;
    
    

    public AppUserRole(Long userRoleId, Long userId, Long roleId) {
        this.userRoleId = userRoleId;
        this.userId = userId;
        this.roleId = roleId;
    }

    public AppUserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    
    public AppUserRole() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    
    @Override
    public String toString() {
        return "com.shristy.web.projectmanagement.entity.AppUserRole[ id=" + userRoleId + " ]";
    }

    @Override
    public int compareTo(AppUserRole o) {
           if (getUserRoleId() == null || o.getUserRoleId() == null) {
      return 0;
    }
    return getUserRoleId().compareTo(o.getUserRoleId());
    }
    
}
