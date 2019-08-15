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
public class Subject {
	
	private Long subjectID;
	private String subjectName;
	private Integer numberOfLecturesPerWeek;
	private boolean islab;
	private String department;
	
	public Subject(Long id, String name, Integer lectures, boolean lab, String dept){
		this.subjectID=id;
		this.subjectName=name;
		this.numberOfLecturesPerWeek=lectures;
		this.islab=lab;
		this.department=dept;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Integer getNumberOfLecturesPerWeek() {
		return numberOfLecturesPerWeek;
	}
	public void setNumberOfLecturesPerWeek(Integer numberOfLecturesPerWeek) {
		this.numberOfLecturesPerWeek = numberOfLecturesPerWeek;
	}
	public Long getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(Long subjectID) {
		this.subjectID = subjectID;
	}

	public boolean isIslab() {
		return islab;
	}

	public void setIslab(boolean islab) {
		this.islab = islab;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
}
