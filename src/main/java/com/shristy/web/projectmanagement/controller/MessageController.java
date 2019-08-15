/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.controller;

import com.shristy.web.projectmanagement.RoutineType;
import com.shristy.web.projectmanagement.entity.AppUser;
import com.shristy.web.projectmanagement.entity.Courses;
import com.shristy.web.projectmanagement.entity.Messages;
import com.shristy.web.projectmanagement.entity.Room;
import com.shristy.web.projectmanagement.entity.Subjects;
import com.shristy.web.projectmanagement.entity.Teacher;
import com.shristy.web.projectmanagement.entity.TeacherSubject;
import com.shristy.web.projectmanagement.entity.Timetable;
import com.shristy.web.projectmanagement.entity.UserImage;
import com.shristy.web.projectmanagement.entity.ga.ClassRoom;
import com.shristy.web.projectmanagement.entity.ga.Combination;
import com.shristy.web.projectmanagement.entity.ga.Course;
import com.shristy.web.projectmanagement.entity.ga.Day;
import com.shristy.web.projectmanagement.entity.ga.GeneticAlgorithm;
import com.shristy.web.projectmanagement.entity.ga.Lecture;
import com.shristy.web.projectmanagement.entity.ga.Professor;
import com.shristy.web.projectmanagement.entity.ga.StudentGroups;
import com.shristy.web.projectmanagement.entity.ga.Subject;
import com.shristy.web.projectmanagement.entity.ga.TimeSlot;
import com.shristy.web.projectmanagement.entity.ga.TimeTable;
import com.shristy.web.projectmanagement.repository.RoomRepository;
import com.shristy.web.projectmanagement.repository.SubjectsRepository;
import com.shristy.web.projectmanagement.repository.TimetableRepository;
import com.shristy.web.projectmanagement.repository.UserImageRepository;
import com.shristy.web.projectmanagement.repository.impl.AppUserServiceImpl;

import com.shristy.web.projectmanagement.repository.impl.CoursesServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.MessageServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.RoomServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.SubjectsServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeacherServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeacherSubjectServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TimetableServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.UserImageServiceImpl;
import com.shristy.web.projectmanagement.utils.WebUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/messages")
public class MessageController {

    //this class takes all inputs from a file. courseID, courseName, roomID's, subjects and professors associated with course
    //currently hardcoded by taking one course with 6 subjects and 6 teachers
    @Autowired
    private TeacherServiceImpl tr;
    @Autowired
    private SubjectsServiceImpl sr;
    @Autowired
    private RoomServiceImpl rr;
    @Autowired
    private CoursesServiceImpl cr;
    @Autowired
    private SubjectsRepository srr;
    @Autowired
    private RoomRepository rrr;
    
    @Autowired
    private UserImageServiceImpl ui;
    //this class takes all inputs from a file. courseID, courseName, roomID's, subjects and professors associated with course
    //currently hardcoded by taking one course with 6 subjects and 6 teachers
    private ArrayList<Subject> subjects = new ArrayList();
    private ArrayList<Professor> professors = new ArrayList();
    private ArrayList<TimeTable> timetables = new ArrayList();
    private ArrayList<Lecture> classes = new ArrayList<>();
    private ArrayList<Combination> combinations = new ArrayList<>();

    @Autowired
    private TeacherSubjectServiceImpl tsr;

    @Autowired
    private TimetableServiceImpl ttrimpl;
    @Autowired
    private TimetableRepository ttr;
    
     @Autowired
    private AppUserServiceImpl aur;
     
       @Autowired
    private MessageServiceImpl mr;

    private Subjects subject = new Subjects();
    private Teacher teacher = new Teacher();

    @GetMapping
    public String msgPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();
          
        String userInfo = WebUtils.toString(loginedUser);
      model.addAttribute("msges",mr.getAll());
     // model.addAttribute("name", principal.getName());
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
      
       List<AppUser> users= aur.getAll();
       for (int i=0;i<users.size();i++) {
           
            if (users.get(i).getUserName().equals(principal.getName())) {
               //  System.out.println("Adminid  "+ users.get(i).getUserId());
              model.addAttribute("cuser",users.get(i));
               
            }
       }
     
        
        return "messagePage";
//        return "adminPage";
    }

       
    @RequestMapping(value = "/send", method = RequestMethod.POST)

    public String save(Principal principal,@RequestParam("username") String user_name,
            @RequestParam("message") String msg) {
         String name = principal.getName();
        // AppUser user= aur.getById(aur.getIdByName(name));
        // AppUser touser= aur.getById(aur.getIdByName(user_name));
         //Messages message= new Messages();
         System.out.println("Adminid  "+ name);
          System.out.println("Toid  "+ user_name);
             List<AppUser> users= aur.getAll();
       Messages message = new Messages();
        Messages message2 = new Messages();
      long id;
      if(mr.lastId()==null)
      { id=1;}
      else 
      {  id=mr.lastId().getMessageId()+1;}
       message.setMessageId(id);
        message2.setMessageId(id+1);
        message.setMsgTxt(msg);
         message2.setMsgTxt(msg);
        for (int i=0;i<users.size();i++) {
           
            if (users.get(i).getUserName().equals(name)) {
               //  System.out.println("Adminid  "+ users.get(i).getUserId());
              message.setUser(users.get(i));
               message.setFromuser(users.get(i));
                message2.setFromuser(users.get(i));
            }
            if( users.get(i).getUserName().equals(user_name)) {
                // System.out.println("Toid  "+ users.get(i).getUserId());
                 message.setTouser(users.get(i));
                 message2.setUser(users.get(i));
                  message2.setTouser(users.get(i));
            }
  
    }
       // System.out.println("Message "+ message.toString());
        mr.save(message);
        mr.save(message2); 
         return "redirect:/messages";
    }

      @RequestMapping(value = "/user")

    public String user(Principal principal,Model model) {
          model.addAttribute("msges",mr.getAll());
      model.addAttribute("name", principal.getName());
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
      
       List<AppUser> users= aur.getAll();
       for (int i=0;i<users.size();i++) {
           
            if (users.get(i).getUserName().equals(principal.getName())) {
               //  System.out.println("Adminid  "+ users.get(i).getUserId());
              model.addAttribute("cuser",users.get(i));
               
            }
       }
     
        
        return "user/messagePage";
         
    }
    
    
     @RequestMapping(value = "/send/user", method = RequestMethod.POST)

    public String saveUsermsg(Principal principal,@RequestParam("username") String user_name,
            @RequestParam("message") String msg) {
         String name = principal.getName();
        // AppUser user= aur.getById(aur.getIdByName(name));
        // AppUser touser= aur.getById(aur.getIdByName(user_name));
         //Messages message= new Messages();
         System.out.println("Adminid  "+ name);
          System.out.println("Toid  "+ user_name);
             List<AppUser> users= aur.getAll();
       Messages message = new Messages();
        Messages message2 = new Messages();
      long id;
      if(mr.lastId()==null)
      { id=1;}
      else 
      {  id=mr.lastId().getMessageId()+1;}
       message.setMessageId(id);
        message2.setMessageId(id+1);
        message.setMsgTxt(msg);
         message2.setMsgTxt(msg);
        for (int i=0;i<users.size();i++) {
           
            if (users.get(i).getUserName().equals(name)) {
               //  System.out.println("Adminid  "+ users.get(i).getUserId());
              message.setUser(users.get(i));
               message.setFromuser(users.get(i));
                message2.setFromuser(users.get(i));
            }
            if( users.get(i).getUserName().equals(user_name)) {
                // System.out.println("Toid  "+ users.get(i).getUserId());
                 message.setTouser(users.get(i));
                 message2.setUser(users.get(i));
                  message2.setTouser(users.get(i));
            }
  
    }
       // System.out.println("Message "+ message.toString());
        mr.save(message);
        mr.save(message2); 
         return "redirect:/messages/user";
    }

    
     @RequestMapping(value = "/student")

    public String userstd(Principal principal,Model model) {
          model.addAttribute("msges",mr.getAll());
      model.addAttribute("name", principal.getName());
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
      
       List<AppUser> users= aur.getAll();
       for (int i=0;i<users.size();i++) {
           
            if (users.get(i).getUserName().equals(principal.getName())) {
               //  System.out.println("Adminid  "+ users.get(i).getUserId());
              model.addAttribute("cuser",users.get(i));
               
            }
       }
     
        
        return "user/student/messagePage";
         
    }
    
    
     @RequestMapping(value = "/send/student", method = RequestMethod.POST)

    public String savestdmsg(Principal principal,@RequestParam("username") String user_name,
            @RequestParam("message") String msg) {
         String name = principal.getName();
        // AppUser user= aur.getById(aur.getIdByName(name));
        // AppUser touser= aur.getById(aur.getIdByName(user_name));
         //Messages message= new Messages();
         System.out.println("Adminid  "+ name);
          System.out.println("Toid  "+ user_name);
             List<AppUser> users= aur.getAll();
       Messages message = new Messages();
        Messages message2 = new Messages();
      long id;
      if(mr.lastId()==null)
      { id=1;}
      else 
      {  id=mr.lastId().getMessageId()+1;}
       message.setMessageId(id);
        message2.setMessageId(id+1);
        message.setMsgTxt(msg);
         message2.setMsgTxt(msg);
        for (int i=0;i<users.size();i++) {
           
            if (users.get(i).getUserName().equals(name)) {
               //  System.out.println("Adminid  "+ users.get(i).getUserId());
              message.setUser(users.get(i));
               message.setFromuser(users.get(i));
                message2.setFromuser(users.get(i));
            }
            if( users.get(i).getUserName().equals(user_name)) {
                // System.out.println("Toid  "+ users.get(i).getUserId());
                 message.setTouser(users.get(i));
                 message2.setUser(users.get(i));
                  message2.setTouser(users.get(i));
            }
  
    }
       // System.out.println("Message "+ message.toString());
        mr.save(message);
        mr.save(message2); 
         return "redirect:/messages/student";
    }

    

    
}
