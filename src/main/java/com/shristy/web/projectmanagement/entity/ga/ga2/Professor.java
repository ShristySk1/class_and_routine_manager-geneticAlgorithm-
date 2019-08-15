package com.shristy.web.projectmanagement.entity.ga.ga2;


import java.util.ArrayList;
import java.util.List;

public class Professor {
	private Long professorID;
	private String professorName;
	private ArrayList <String> subjectsTaught = new ArrayList();
	
	public Professor(Long id, String name,List<String> subj){
		this.professorID=id;
		this.professorName=name;
                this.subjectsTaught=(ArrayList<String>) subj;
//		String[] subjectNames=subj.split("/");
//		for(int i=0; i<subjectNames.length; i++){
//			this.subjectsTaught.add(subjectNames[i]);
//		}

	}
	
	public Long getProfessorID() {
		return professorID;
	}
	public void setProfessorID(Long professorID) {
		this.professorID = professorID;
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public ArrayList<String> getSubjectTaught() {
		return subjectsTaught;
	}

	public void setSubjectTaught(ArrayList<String> subjectTaught) {
		this.subjectsTaught = subjectTaught;
	}
	
	
}
