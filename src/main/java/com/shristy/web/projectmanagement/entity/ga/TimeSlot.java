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
public class TimeSlot {
	//private int slotTime;
	private Lecture lecture;
	
//	public TimeSlot(int t){
//		this.setSlotTime(t);
//	}

//	public int getSlotTime() {
//		return slotTime;
//	}
//
//	public void setSlotTime(int slotTime) {
//		this.slotTime = slotTime;
//	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}
}
