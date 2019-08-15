/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class FormValue2 {
//    private List<Long> semId;
//    private int semRoomNo;
//    private List<Long> roomId;
private List<TeachSub> teachsubs=new ArrayList<>();
private String routinename;
private List<FormSubject> formsubjects=new ArrayList<>();



   public FormValue2() {
    }

  public void addTeachSub(TeachSub teachsub) {
        this.teachsubs.add(teachsub);
    }
 public void addSub(FormSubject formsubject) {
        this.formsubjects.add(formsubject);
    }


    public List<TeachSub> getTeachsubs() {
        return teachsubs;
    }

    public void setTeachsubs(List<TeachSub> teachsubs) {
        this.teachsubs = teachsubs;
    }

    public List<FormSubject> getFormsubjects() {
        return formsubjects;
    }

    public void setFormsubjects(List<FormSubject> formsubjects) {
        this.formsubjects = formsubjects;
    }




 
  

 

    public String getRoutinename() {
        return routinename;
    }

    public void setRoutinename(String routinename) {
        this.routinename = routinename;
    }


  
   
}
