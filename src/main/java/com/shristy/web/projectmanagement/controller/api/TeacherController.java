/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.controller.api;

import com.shristy.web.projectmanagement.entity.Courses;
import com.shristy.web.projectmanagement.entity.Teach;
import com.shristy.web.projectmanagement.entity.Teacher;
import com.shristy.web.projectmanagement.entity.Teachertable;
import com.shristy.web.projectmanagement.entity.Timetable;
import com.shristy.web.projectmanagement.repository.impl.CoursesServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeacherServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeachertableServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TimetableServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController 
@RequestMapping(value = "/rest/teacher")
//    @RequestMapping(value = "/main/tabs/routine")
//@CrossOrigin(origins = "http://localhost:8100")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TeacherController {
    @Autowired
    private TeacherServiceImpl service;
    @GetMapping()
    @CrossOrigin(origins = "*", allowedHeaders = "*")
 public List<Teach> gettimetable(){
     List<Teach> tea=new ArrayList<>();
     for(Teacher t :service.getAll()){
         tea.add(new Teach((long)t.getTeacherId(),t.getTeacherName()));
     }
        return tea;
    }
 
    
}
