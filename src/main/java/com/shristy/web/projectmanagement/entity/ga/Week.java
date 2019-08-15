/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity.ga;



/**
 *
 * @author Administrator
 */
import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class Week {
	private String[] weekDaysName;
	private ArrayList <Day> weekDays=new ArrayList<Day>();
	
public ArrayList<Day> getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(ArrayList<Day> weekDays) {
		this.weekDays = weekDays;
	}

public Week(){
	this.weekDaysName = new DateFormatSymbols().getWeekdays();
    for (int i = 1; i < weekDaysName.length; i++) {
        //System.out.println("weekday = " + weekDaysName[i]);
    	if(!(weekDaysName[i].equalsIgnoreCase("Sunday"))&&!(weekDaysName[i].equalsIgnoreCase("Saturday"))){
    	Day newday=new Day(weekDaysName[i]);
    	weekDays.add(newday);
    		}
    	}
	}
}
