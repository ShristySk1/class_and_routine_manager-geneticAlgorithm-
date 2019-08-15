/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class FormValue {
    private List<Long> semId;
    private int semRoomNo;
    private List<Long> roomId;

    public FormValue() {
    }

    public void addSem(Long s) {
        this.semId.add(s);
    }
    public List<Long> getsemId() {
        return semId;
    }

    public void setsemId(List<Long> semId) {
        this.semId = semId;
    }

    public int getSemRoomNo() {
        return semRoomNo;
    }

    public void setSemRoomNo(int semRoomNo) {
        this.semRoomNo = semRoomNo;
    }

    public List<Long> getRoomId() {
        return roomId;
    }

    public void setRoomId(List<Long> roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "FormValue{" + "semId=" + semId + ", semRoomNo=" + semRoomNo + ", roomId=" + roomId + '}';
    }

   
}
