/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.controller.api;

import com.shristy.web.projectmanagement.entity.Courses;
import com.shristy.web.projectmanagement.repository.impl.CoursesServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController 
@RequestMapping(value = "/rest/courses")
public class CourseRestController {
    @Autowired
    private CoursesServiceImpl service;
    @GetMapping()
    public List<Courses> getCourses(){
        return service.getAll();
    }
    
}
