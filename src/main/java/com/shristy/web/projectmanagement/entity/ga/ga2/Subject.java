package com.shristy.web.projectmanagement.entity.ga.ga2;


public class Subject {
	
	private Long subjectID;
	private String subjectName;
	private int numberOfLecturesPerWeek;
	private boolean islab;
	private String department;
	
	Subject(Long id, String name, int lectures, boolean lab, String dept){
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
	public int getNumberOfLecturesPerWeek() {
		return numberOfLecturesPerWeek;
	}
	public void setNumberOfLecturesPerWeek(int numberOfLecturesPerWeek) {
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
