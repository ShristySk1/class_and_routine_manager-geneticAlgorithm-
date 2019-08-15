/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "room")
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r")})
public class Room implements Serializable {

    @Size(max = 20)
    @Column(name = "room_assigned")
    private String roomAssigned;
    @Size(max = 20)
    @Column(name = "room_value")
    private String roomValue;
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    @ManyToOne
    private Department departmentId;
    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "room_id")
    private Long roomId;

    public Room() {
    }

    public Room(Long roomId) {
        this.roomId = roomId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomAssigned() {
        return roomAssigned;
    }

    public void setRoomAssigned(String roomAssigned) {
        this.roomAssigned = roomAssigned;
    }

 
   

    @Override
    public String toString() {
        return "com.shristy.web.projectmanagement.entity.Room[ roomId=" + roomId + " ]";
    }



 

    public String getRoomValue() {
        return roomValue;
    }

    public void setRoomValue(String roomValue) {
        this.roomValue = roomValue;
    }

   

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }




    
}
