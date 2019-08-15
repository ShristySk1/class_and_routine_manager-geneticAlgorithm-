/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.controller;

import com.shristy.web.projectmanagement.RoutineType;
import com.shristy.web.projectmanagement.entity.Courses;
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

import com.shristy.web.projectmanagement.repository.impl.CoursesServiceImpl;
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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;


/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/admin/subjects")
public class SubjectController {

    @Autowired
    private SubjectsServiceImpl sr;
     @Autowired
    private CoursesServiceImpl cr;
      @Autowired
    private TeacherSubjectServiceImpl tsr;
      @Autowired
    private TeacherServiceImpl tr;
       @Autowired
    private UserImageServiceImpl ui;

    @GetMapping
    public String subjectPage(Model model,Principal principal) {
List<Subjects> list=sr.getAll();
Collections.sort(list);
        model.addAttribute("Subjects",list);
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
        return "admin/subjects/subjectsPage";

    }

    @GetMapping(value = "/addSubject")
    public String add(Model model,Principal principal) {
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
        return "admin/subjects/addSubject";
    }

    @GetMapping(value = "/addSpecSubject/{cid}")
    public String addSpec(@PathVariable("cid") long cid,Model model,Principal principal) {
        model.addAttribute("courses",cr.getById(cid));
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
        return "admin/subjects/addSpecSubject";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)

    public String save(@ModelAttribute("subject") Subjects subject, @RequestParam("subjectsName") String subjectName,
            @RequestParam("subjectsCode") String subjectCode) {

        sr.save(new Subjects(subject.getSubjectId(), subjectName, subjectCode));

        return "redirect:/admin/teachers";
    }
    
    @RequestMapping(value = "/saveSubject/{cid}", method = RequestMethod.POST)

    public String saveSubject(@PathVariable("cid") long cid,@ModelAttribute("subject") Subjects subject,
            @RequestParam("subjectName") String subjectName,
            @RequestParam("subjectCode") String subjectCode,
            @RequestParam("periodPerWeek") Integer periodPerWeek,
            @RequestParam("isLab") Boolean lab) {
 //new Subjects(subjectName, subjectCode, cid, Integer.MIN_VALUE, Boolean.FALSE, null, null)
    Long sid = null;
     sid = sr.lastId().getSubjectId() + 1;
       // System.out.println(sid);
     sr.save(new Subjects(sid, subjectName, subjectCode, periodPerWeek, lab,cr.getById(cid),cr.getById(cid).getDepartmentId()));   
       
        return "redirect:/admin/courses/courseSubject/{cid}";
    }
    
    @RequestMapping(value = "/save/{sid}/{cid}", method = RequestMethod.POST)

    public String saveSub(@PathVariable("sid") long sid,@ModelAttribute("subject") Subjects subject,
            @RequestParam("subject_name") String subjectName,
            @RequestParam("subject_code") String subjectCode,
            @RequestParam("period_per_week") Integer periodPerWeek,
            @RequestParam("is_lab") Boolean lab, @PathVariable("cid") long cid,Model model) {
 //new Subjects(subjectName, subjectCode, cid, Integer.MIN_VALUE, Boolean.FALSE, null, null)
    
       // System.out.println(sid);
       // long cid= sr.getById(sid).getCourseId().getCoursesId();
     //   RedirectAttributes ra = new RedirectAttributesModelMap();
     //   ra.addFlashAttribute("cid", sr.getById(sid).getCourseId().getCoursesId());
     sr.save(new Subjects(sid, subjectName, subjectCode, periodPerWeek, lab,sr.getById(sid).getCourseId(),sr.getById(sid).getDepartmentId()));   
    // RedirectView redirectview = new RedirectView();
   //  redirectview.setUrl("/admin/courses/courseSubject/{cid}");
        //System.out.println(cid);
        return "redirect:/admin/courses/courseSubject/{cid}";
    }
    
    
    @GetMapping(value = "/edit/{sid}")
    public String edit(@PathVariable("sid") long sid, Model model,Principal principal) {
        model.addAttribute("subject", sr.getById(sid));
        model.addAttribute("course",sr.getById(sid).getCourseId());
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
        return "admin/subjects/editSubject";
    }

    
    @GetMapping(value = "/delete/{id}/{cid}")
    public String delete(@PathVariable("id") long id,@PathVariable("cid") long cid) {

       
       
        for(TeacherSubject ts : tsr.getAll()){
           if(ts.getSubjectId().getSubjectId()== id){
              tsr.delete(ts.getTsId());
               tr.delete(ts.getTeacherId().getTeacherId());
            
           }
        }
         sr.delete(id);
        return "redirect:/admin/courses/courseSubject/{cid}";
    }
}
