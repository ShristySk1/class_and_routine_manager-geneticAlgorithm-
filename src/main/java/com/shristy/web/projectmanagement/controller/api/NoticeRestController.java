/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.controller.api;

import com.shristy.web.projectmanagement.entity.Todolist;
import com.shristy.web.projectmanagement.entity.todo;
import com.shristy.web.projectmanagement.repository.TodolistRepository;

import com.shristy.web.projectmanagement.repository.impl.TodolistServiceImpl;
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
@RequestMapping(value = "/rest/notice")
public class NoticeRestController {
    @Autowired
    private TodolistServiceImpl service;
        @Autowired
    private TodolistRepository repo;
    @GetMapping()
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Todolist> getCourses(){
        return service.getAll();
    }
        @GetMapping("/a")

  public List<todo> getC(){
        todo list=new todo();
      List<todo> ls=new ArrayList<>();
    List<Todolist> tt=repo.findByC();
        for(Todolist euta:tt){
            ls.add(new todo(euta.getListNotice()));
        }
    
//      list.setNotice("helo swarina");
//      
//      ls.add(list);
//              ls.add(new todo("helo rohini"));
return ls;
        }
    
}
