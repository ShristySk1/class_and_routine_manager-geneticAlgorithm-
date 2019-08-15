/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.controller.api;

import com.shristy.web.projectmanagement.entity.Courses;
import com.shristy.web.projectmanagement.entity.Teachertable;
import com.shristy.web.projectmanagement.entity.Timetable;
import com.shristy.web.projectmanagement.repository.impl.CoursesServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeachertableServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TimetableServiceImpl;
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
@RequestMapping(value = "/rest/teacher/timetable")
//    @RequestMapping(value = "/main/tabs/routine")
//@CrossOrigin(origins = "http://localhost:8100")
public class TeacherTimetableController {
    @Autowired
    private TeachertableServiceImpl service;
    @GetMapping()
    @CrossOrigin(origins = "*", allowedHeaders = "*")
 public List<Teachertable> gettimetable(){
        return service.getAll();
    }
 
    
}
