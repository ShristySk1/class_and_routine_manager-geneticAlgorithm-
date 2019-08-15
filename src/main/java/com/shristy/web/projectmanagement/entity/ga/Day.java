/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity.ga;

import java.util.ArrayList;
import java.util.Calendar;

public class Day {
	//private String name;
	private ArrayList <TimeSlot> timeSlot=new ArrayList();

    public Day() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	public ArrayList<TimeSlot> getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(ArrayList<TimeSlot> timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Day(String inputname){
//		this.setName(inputname);
		for(int i=9; i<19; i++){
			if(i!=15){
				TimeSlot ts=new TimeSlot(/*i*/);
				timeSlot.add(ts);
			}
			
		}
//		TimeSlot ts1=new TimeSlot(12);
//		ts1.setSubject("BREAK");
//     	timeSlot.add(ts1);
//		for(int i=1; i<4; i++){
//			TimeSlot ts=new TimeSlot(i);
//			timeSlot.add(ts);
//		}
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
      
}
