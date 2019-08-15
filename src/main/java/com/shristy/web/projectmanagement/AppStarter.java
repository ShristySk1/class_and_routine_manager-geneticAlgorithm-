package com.shristy.web.projectmanagement;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shristy.web.projectmanagement.entity.Feedback;
import com.shristy.web.projectmanagement.repository.StudentRepository;
import com.shristy.web.projectmanagement.repository.impl.FeedbackServiceImpl;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
// */
@EnableCaching
@EnableAutoConfiguration
@SpringBootApplication
public class AppStarter {
    public static void main(String[] args){
        SpringApplication.run(AppStarter.class, args);
    }
  
  
    //feedback data read
    
    @Bean
    CommandLineRunner runner(FeedbackServiceImpl fsi ){
    return args ->{
    //read json data
         
        ObjectMapper mapper= new ObjectMapper();
        TypeReference <List<Feedback>> tr= new TypeReference<List<Feedback>>(){};
        InputStream is= TypeReference.class.getResourceAsStream("/json/feedback.json");
        try{
            List<Feedback> feedback =mapper.readValue(is, tr);
            fsi.save(feedback);
            System.out.println("Feedback saved");  
            
        
        }catch(IOException e){
            System.out.println("not saved"+e.getMessage());
        }
    };
    
    }
}
