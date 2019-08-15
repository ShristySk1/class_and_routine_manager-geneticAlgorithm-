/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.controller.api;


import com.shristy.web.projectmanagement.entity.swarina.Studentt;
import com.shristy.web.projectmanagement.repository.impl.swarina.StudenttServiceImpl;
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
@RequestMapping(value = "/rest/students")
//    @RequestMapping(value = "/main/tabs/routine")
//@CrossOrigin(origins = "http://localhost:8100")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudenttController {
    @Autowired
    private StudenttServiceImpl service;
    @GetMapping()
    @CrossOrigin(origins = "*", allowedHeaders = "*")
 public List<Studentt> getstudent(){
      return service.getAll();

    }
}
