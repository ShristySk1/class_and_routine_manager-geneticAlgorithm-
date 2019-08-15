/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.controller.api;

import com.shristy.web.projectmanagement.entity.Subjects;
import com.shristy.web.projectmanagement.entity.Subjectsss;
import com.shristy.web.projectmanagement.repository.impl.SubjectsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */

@RestController 
@RequestMapping(value = "/rest/subjects")
public class SubjectsController {
    @Autowired
    private SubjectsServiceImpl service;
    @GetMapping()
    public List<Subjectsss> getSubject(){
        
        List<Subjectsss> subs=new ArrayList<>();
       for(Subjects s: service.getAll()){
            subs.add(new Subjectsss(s.getSubjectName(),s.getSubjectCode(),s.getCourseId().getCoursesId()));
      }
       return subs;
}
}