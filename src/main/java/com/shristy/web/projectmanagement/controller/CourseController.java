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

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/admin/courses")
public class CourseController {

    //this class takes all inputs from a file. courseID, courseName, roomID's, subjects and professors associated with course
    //currently hardcoded by taking one course with 6 subjects and 6 teachers
    @Autowired
    private TeacherServiceImpl tr;
    @Autowired
    private SubjectsServiceImpl sr;
    @Autowired
    private RoomServiceImpl rr;
    @Autowired
    private UserImageServiceImpl ui;
    @Autowired
    private CoursesServiceImpl cr;
    @Autowired
    private SubjectsRepository srr;
    @Autowired
    private RoomRepository rrr;
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

    private Subjects subject = new Subjects();
    private Teacher teacher = new Teacher();

    @GetMapping
    public String coursePage(Model model, Principal principal) {

        List<Courses> listcr = cr.getAll();
        Collections.sort(listcr);
        model.addAttribute("Courses", listcr);
         String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }

        return "admin/courses/coursesPage";
//        return "adminPage";
    }

    @GetMapping(value = "/courseSubject/{id}")//,params = "id")
    public String courseSubject(@PathVariable("id") long id, Model model,Principal principal) {
        // public String courseSubject(@RequestParam long id, Model model) {
//List<Subjects> list=cr.getById((long) id).getSubjectsList();
List<Subjects> list=srr.getSubjectSem((long)id);
Collections.sort(list);
        model.addAttribute("subjects", list);
        model.addAttribute("courses", cr.getById(id));
         String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
        return "admin/courses/courseSubjectPage";
    }

   /* @GetMapping(value = "/courseSubTeacher/{id}")//,params = "id")
    public String courseSubTeacher(@PathVariable("id") long id, Model model) {
        // public String courseSubject(@RequestParam long id, Model model) {
        List<TeacherSubject> teachSub = tsr.getAll();
      //   List<Teacher> teach = new ArrayList<Teacher>();
        for (TeacherSubject ts : teachSub) {
            {
                Long sid = ts.getSubjectId().getSubjectId();
                if (sid == id) {
                   // teach.add(tr.getById(sid));
                   model.addAttribute("teachers",tr.getById(sid));
                }
            }
           // model.addAttribute("teachers",teach);
        }
       // model.addAttribute("courseId",sr.getById(id).getCourseId().getCoursesId());
        //  model.addAttribute("DepartmentId",sr.getById(id).getDepartmentId().getDepartmentId());
        model.addAttribute("subject", sr.getById(id));
        return "admin/courses/courseSubTeacherPage";
    }*/
    
      @GetMapping(value = "/courseSubTeacher/{id}")//,params = "id")
     public String courseSubTeacher(@PathVariable("id") long id,Model model,Principal principal){
   // public String courseSubject(@RequestParam long id, Model model) {
        List<TeacherSubject> teachSub= tsr.getAll();
        List<Teacher> teach = new ArrayList<Teacher>();
        for(TeacherSubject ts: teachSub){
           {
              if( ts.getSubjectId().getSubjectId()== id){
                  teach.add(ts.getTeacherId());
               
              }
           }
           
        }
       // model.addAttribute("courseId",sr.getById(id).getCourseId().getCoursesId());
       //  model.addAttribute("DepartmentId",sr.getById(id).getDepartmentId().getDepartmentId());
        model.addAttribute("teacher", teach);
      model.addAttribute("subject",sr.getById(id));
      //model.addAttribute("subjectId",sr.getById(id).getSubjectId());
      //Subjects subject= sr.getById(id);
        //  RedirectAttributes ra = new RedirectAttributesModelMap();
          //ra.addAttribute("subjectId",sr.getById(id).getSubjectId());
       String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
        return "admin/courses/courseSubTeacherPage";
    }


    @GetMapping(value = "/edit/{id}")
    public String editCourse(@PathVariable("id") long id, Model model,Principal principal) {
        model.addAttribute("course", cr.getById(id));
         String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
        return "admin/courses/editCourse";
    }

    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)

    public String saveCourse(@ModelAttribute("course") Courses course, @PathVariable("id") long id, @RequestParam("course_name") String course_name) {
        //  teacher.setTeacherPicture("");
        //  teacher.setTeacherWorkingHr(null);

        cr.save(new Courses(id, course_name, course.getDepartmentId()));
        return "redirect:/admin/courses";
    }
    
    

   

}
