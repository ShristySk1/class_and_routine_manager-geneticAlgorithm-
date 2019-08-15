/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity.ga;

import java.util.ArrayList;
import java.util.List;

public class Combination {
	
	private int sizeOfClass;
	private ArrayList<String> subjectCombination=new ArrayList<>();

	public Combination(List<String> subjects, int size) {
		// TODO Auto-generated constructor stub
//		setSizeOfClass(size);		
//		String[] subj = subjects.split("/");
//		for(int i=0; i<subj.length;i++){
			subjectCombination=(ArrayList<String>) subjects;
		
	}

	public int getSizeOfClass() {
		return sizeOfClass;
	}

	public void setSizeOfClass(int sizeOfClass) {
		this.sizeOfClass = sizeOfClass;
	}

	public ArrayList<String> getSubjects() {
		return subjectCombination;
	}

	public void setSubjects(ArrayList<String> subjects) {
		this.subjectCombination = subjects;
	}
}
