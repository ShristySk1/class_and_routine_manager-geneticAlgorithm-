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
public class TimetableForm1 {
    List<Timetablenext> listtimetable;

    public TimetableForm1(List<Timetablenext> listtimetable) {
        this.listtimetable = listtimetable;
    }

    public TimetableForm1() {
       
    }

    public List<Timetablenext> getListtimetable() {
        return listtimetable;
    }

    public void setListtimetable(List<Timetablenext> listtimetable) {
        this.listtimetable = listtimetable;
    }
    
}
