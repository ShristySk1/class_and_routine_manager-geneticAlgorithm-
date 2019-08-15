/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.controller.api;

import com.shristy.web.projectmanagement.entity.Feedback;
import com.shristy.web.projectmanagement.repository.impl.FeedbackServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */
@RestController 
@RequestMapping(value = "/api/feedbacks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeedbackRestController {
    @Autowired
    private FeedbackServiceImpl service;
    @GetMapping()
    @CrossOrigin(origins = "*", allowedHeaders = "*")
 public List<Feedback> getfeedback(){
        return service.getAll();
}

    public FeedbackRestController(FeedbackServiceImpl service) {
        this.service = service;
    }
 
 
 
}
