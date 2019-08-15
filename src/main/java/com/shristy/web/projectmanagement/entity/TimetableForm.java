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
public class TimetableForm {
    List<Timetable> listtimetable;

    public TimetableForm(List<Timetable> listtimetable) {
        this.listtimetable = listtimetable;
    }

    public TimetableForm() {
       
    }

    public List<Timetable> getListtimetable() {
        return listtimetable;
    }

    public void setListtimetable(List<Timetable> listtimetable) {
        this.listtimetable = listtimetable;
    }
    
}
