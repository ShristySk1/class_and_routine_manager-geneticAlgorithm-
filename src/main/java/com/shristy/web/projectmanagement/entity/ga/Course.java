/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity.ga;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Course {
    
	//represents each course
	
	private Long courseID;
	private String courseName;
	private ArrayList<Subject> subjectsIncluded= new ArrayList<Subject>();
	private ArrayList<Combination> combinations=new ArrayList<Combination>();
	
	public ArrayList<Combination> getCombinations() {
		return combinations;
	}

	public void setCombinations(ArrayList<Combination> combinations) {
		this.combinations = combinations;
	}
	private ArrayList<StudentGroups> studentGroups=new ArrayList<StudentGroups>();
	
	public ArrayList<StudentGroups> getStudentGroups() {
		return studentGroups;
	}

	public void setStudentGroups(ArrayList<StudentGroups> studentGroups) {
		this.studentGroups = studentGroups;
	}

	public Course(Long id, String name, ArrayList<Subject> subjects){
		System.out.println("creating new course.......");
		this.courseID=id;
		this.courseName=name;
		this.subjectsIncluded=subjects;
	}
	
	public void createStudentGroups(){
		int size=0;
		ArrayList <Combination>combs=new ArrayList<Combination>();
		Iterator<Subject> subjectIterator=subjectsIncluded.iterator();
		while(subjectIterator.hasNext()){
			Subject subject=subjectIterator.next();
			Iterator combIterator =combinations.iterator();
			while(combIterator.hasNext()){
				Combination combination = (Combination) combIterator.next();
				ArrayList<String> subjects = combination.getSubjects();
				Iterator<String> subjectItr = subjects.iterator();
				while(subjectItr.hasNext()){
					if(subjectItr.next().equalsIgnoreCase(subject.getSubjectName())){
						size=size+combination.getSizeOfClass();
						if(!combs.contains(combination.getSubjects())){
						combs.add(combination);
						}
					}
				}
			}System.out.println("courseName: "+this.courseName+"/"+subject.getSubjectName()+"/"+subject.getNumberOfLecturesPerWeek());
                        System.out.println("subjectcomb:"+ size+combs+ subject.getSubjectName()+ subject.isIslab()+ subject.getDepartment());
			StudentGroups studentGroup=new StudentGroups(this.courseName+"/"+subject.getSubjectName(), subject.getNumberOfLecturesPerWeek(), size, combs, subject.getSubjectName(), subject.isIslab(), subject.getDepartment());
		    studentGroups.add(studentGroup);
		    size=0;
		}
	}
	
	//creates all possible professor x subject he teaches combinations and saves as lecture objects
	
	public void createCombination(List<String> subjects, Integer size){
		Combination combination=new Combination(subjects, size);
		combinations.add(combination);
	}
	
	public Long getCourseID() {
		return courseID;
	}
	public void setCourseID(Long courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
//	public ArrayList<Professor> getProfessorsTeaching() {
//		return professorsTeaching;
//	}
//	public void setProfessorsTeaching(ArrayList<Professor> professorsTeaching) {
//		this.professorsTeaching = professorsTeaching;
//	}
	public ArrayList<Subject> getSubjectsTaught() {
		return subjectsIncluded;
	}
	public void setSubjectsTaught(ArrayList<Subject> subjectsTaught) {
		this.subjectsIncluded = subjectsTaught;
	}
	
}
