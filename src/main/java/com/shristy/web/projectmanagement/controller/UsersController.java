/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.controller;

import com.shristy.web.projectmanagement.entity.Student;
import com.shristy.web.projectmanagement.entity.AppRole;
import com.shristy.web.projectmanagement.entity.Courses;
import com.shristy.web.projectmanagement.entity.AppUser;
import com.shristy.web.projectmanagement.entity.Messages;
import com.shristy.web.projectmanagement.entity.Subjects;
import com.shristy.web.projectmanagement.entity.Teachertable;
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
import com.shristy.web.projectmanagement.repository.AppUserRepository;
import com.shristy.web.projectmanagement.repository.RoomRepository;
import com.shristy.web.projectmanagement.repository.SubjectsRepository;
import com.shristy.web.projectmanagement.repository.TimetableRepository;
import com.shristy.web.projectmanagement.repository.impl.AppUserRoleServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.AppUserServiceImpl;

import com.shristy.web.projectmanagement.repository.impl.CoursesServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.MessageServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.RoomServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.StudentServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.SubjectsServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeacherServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeacherSubjectServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeachertableServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TimetableServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TodolistServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.UserImageServiceImpl;
import com.shristy.web.projectmanagement.utils.WebUtils;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping(value = "/user")
public class UsersController {

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
    private AppUserServiceImpl ur;
    @Autowired
    private AppUserRoleServiceImpl aur;
    @Autowired
    private StudentServiceImpl stdr;
    @Autowired
    private SubjectsRepository srr;
    @Autowired
    private RoomRepository rrr;

    @Autowired
    BCryptPasswordEncoder encoder;
    //this class takes all inputs from a file. courseID, courseName, roomID's, subjects and professors associated with course
    //currently hardcoded by taking one course with 6 subjects and 6 teachers
    private ArrayList<Subject> subjects = new ArrayList();
    private ArrayList<Professor> professors = new ArrayList();
    private ArrayList<TimeTable> timetables = new ArrayList();
    private ArrayList<Lecture> classes = new ArrayList<>();
    private ArrayList<Combination> combinations = new ArrayList<>();
    private List<Timetable> timetable;
    private ArrayList<Timetable> table1= new ArrayList<>();
    private ArrayList<Timetable> table2= new ArrayList<>();
    private  String str,subject;

    @Autowired
    private TeacherSubjectServiceImpl tsr;

    @Autowired
    private TimetableServiceImpl ttrimpl;
    @Autowired
    private TodolistServiceImpl todo;
    @Autowired
    private TeachertableServiceImpl tet;
    @Autowired
    private TimetableRepository ttr;

    @Autowired
    private AppUserRepository urep;

    @Autowired
    private UserImageServiceImpl ui;
       @Autowired
    private MessageServiceImpl mr;

    private Subjects subjective = new Subjects();
    private Teacher teacher = new Teacher();
    private ArrayList<Messages> messages= new ArrayList<>();

    @GetMapping
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("name", principal.getName());
        String name = principal.getName();
         List<Courses> lists = cr.getAll();
        Collections.sort(lists);

         model.addAttribute("semester",lists);
        model.addAttribute("todolist",todo.getAll());
       // System.out.println(todo.getAll());
          model.addAttribute("cocount",cr.getAll().size());
       model.addAttribute("subcount",sr.getAll().size());
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
              messages=(ArrayList<Messages>) mr.findByUser(us.getUser());
              model.addAttribute("count",messages.size());
               model.addAttribute("msges",messages);
                System.out.println(us.getUser());
            }
        }
          
            List<AppUser> users= ur.getAll();
       for (int i=0;i<users.size();i++) {
           
            if (users.get(i).getUserName().equals(principal.getName())) {
               //  System.out.println("Adminid  "+ users.get(i).getUserId());
              model.addAttribute("cuser",users.get(i));
               
            }
       }
        return "user/userPage";
//        return "adminPage";
    }

    @RequestMapping(value = "/routine", method = RequestMethod.GET)
    public String RoutinePage(Model model, Principal principal) throws ClassNotFoundException, SQLException {

        //102 103 don work
        model.addAttribute("oneB", ttr.findByRoomno("RoomNo(104)"));
        model.addAttribute("twoA", ttr.findByRoomno("RoomNo(201)"));
        model.addAttribute("twoB", ttr.findByRoomno("RoomNo(202)"));
        model.addAttribute("oneA", ttr.findByRoomno("RoomNo(101)"));
        model.addAttribute("name", principal.getName());
        String name = principal.getName();
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                System.out.println(us.getUser());
            }
        }

        return "user/teacher/routinePage";
    }

    @RequestMapping(value = "/teacher/routine", method = RequestMethod.GET)
    public String Routine(Model model, Principal principal) throws ClassNotFoundException, SQLException {

        //102 103 don work
        String name = principal.getName();

        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                //    System.out.println(us.getUser());
                teacherRoutine(us.getUser(), model);
            }
        }

        return "user/teacher/teacherRoutine";
    }

    public void teacherRoutine(AppUser user, Model model) {
        for (Teacher teacher : tr.getAll()) {
            if (teacher.getUser() == user) {
                System.out.println(tsr.getlecture(teacher));
                Long id = teacher.getTeacherId();
               
                System.out.println(id);
                System.out.println(tet.findByTeacher(id));
                try{
                   if (tet.findByTeacher(id).isEmpty()){
                       for(TeacherSubject ts: tsr.getlecture(teacher)){
                   generateTeachertable(ts, id, model);
                       }
                   }
               }
                  catch(NullPointerException n){
                    
               }
               // model.addAttribute("table", tet.findByTeacher(id));
                
                model.addAttribute("mon", tet.getDayTable("Monday",(long)id));
                model.addAttribute("tue", tet.getDayTable("Tuesday",(long)id));
                model.addAttribute("wed", tet.getDayTable("Wednesday",(long)id));
                model.addAttribute("thu", tet.getDayTable("Thursday",(long)id));
                model.addAttribute("fri", tet.getDayTable("Friday",(long)id));
            }
        }
    }

    private void generateTeachertable(TeacherSubject ts, Long tid, Model model) {
        int i = 0;
       
        System.out.println("in generate");
        System.out.println(ts);
       
             subject = ts.getSubjectId().getSubjectName();
          System.out.println(ts);
            str = ts.getSubjectId().getSubjectName() + "#" + ts.getTeacherId().getTeacherName();
              System.out.println(str);
           // i++;
        
      //  for (int j = 0; j < str.length; j++) {
        // long idd=1;
            // System.out.println(idd);
            //  Teachertable teachertable = new Teachertable();
            for (Timetable tt : ttrimpl.getAll()) {
                //  System.out.println(idd);
                Teachertable teachertable = new Teachertable();
                //  teachertable.setId(idd);
                teachertable.setTid(tid);
                teachertable.setDays(tt.getDays());
                teachertable.setRoomno(tt.getRoomno());
                System.out.println(tt);
                if (tt.getSeven15eight05().equals(str)) {
                    System.out.println("true");
                    teachertable.setTime("7:15-8:05 (" + subject + ")");
                }
                if (tt.getEight05eight55().equals(str)) {
                    System.out.println("true");
                    teachertable.setTime("8:05-8:55 (" + subject + ")");
                }
                if (tt.getEight55nine45().equals(str)) {
                    System.out.println("true");
                    teachertable.setTime("8:55-9:45 (" + subject + ")");
                }
                if (tt.getNine45ten35().equals(str)) {
                    System.out.println("true");
                    teachertable.setTime("9:45-10:35 (" + subject + ")");
                }
                if (tt.getTen35eleven25().equals(str)) {
                    System.out.println("true");
                    teachertable.setTime("10:35-11:25 (" + subject + ")");
                }
                if (tt.getEleven5twelve15().equals(str)) {
                    System.out.println("true");
                    teachertable.setTime("11:25-12:15 (" + subject + ")");
                }
                if (tt.getTwelve15one05().equals(str)) {
                    System.out.println("true");
                    teachertable.setTime("12:15-1:05 (" + subject + ")");
                }
                if (tt.getOne5one50().equals(str)) {
                    System.out.println("true");
                    teachertable.setTime("1:05-1:55 (" + subject + ")");
                }

                if (tt.getOne50two45().equals(str)) {
                    System.out.println("true");
                    teachertable.setTime("1:55-2:45 (" + subject + ")");
                }
                if (tt.getTwo45three35().equals(str)) {
                    System.out.println("true");
                    teachertable.setTime("2:45-3:35 (" + subject + ")");
                }
                tet.save(teachertable);
                    // idd++;

        // }
           // }

        }
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String teachers(Model model, Principal principal) {
        model.addAttribute("teachers", tr.getAll());
        String name = principal.getName();
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                System.out.println(us.getUser());
            }
        }
        return "user/teacher/teachersPage";
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public String subjectPage(Model model, Principal principal) throws ClassNotFoundException, SQLException {

        //102 103 don work
        model.addAttribute("Subjects", sr.getAll());
        model.addAttribute("name", principal.getName());
        String name = principal.getName();
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                System.out.println(us.getUser());
            }
        }

        return "user/teacher/subjectsPage";
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String coursePage(Model model, Principal principal) throws ClassNotFoundException, SQLException {

        //102 103 don work
        List<Courses> listcr = cr.getAll();
        Collections.sort(listcr);
        model.addAttribute("Courses", listcr);
        model.addAttribute("name", principal.getName());
        String name = principal.getName();
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                System.out.println(us.getUser());
            }
        }

        return "user/coursesPage";
//        return "adminPage";
    }

    @GetMapping(value = "courses/courseSubject/{id}")//,params = "id")
    public String courseSubject(@PathVariable("id") long id, Model model, Principal principal) {
        // public String courseSubject(@RequestParam long id, Model model) {

        model.addAttribute("subjects", cr.getById((long) id).getSubjectsList());
        model.addAttribute("courses", cr.getById(id));
        String name = principal.getName();
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                System.out.println(us.getUser());
            }
        }
        return "user/courseSubjectPage";
    }

    @GetMapping(value = "courses/courseSubTeacher/{id}")//,params = "id")
    public String courseSubTeacher(@PathVariable("id") long id, Model model, Principal principal) {
        // public String courseSubject(@RequestParam long id, Model model) {
        List<TeacherSubject> teachSub = tsr.getAll();
        List<Teacher> teach = new ArrayList<Teacher>();
        for (TeacherSubject ts : teachSub) {
            {
                if (ts.getSubjectId().getSubjectId() == id) {
                    teach.add(ts.getTeacherId());

                }
            }

        }

        model.addAttribute("teacher", teach);
        model.addAttribute("subject", sr.getById(id));
        String name = principal.getName();
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                System.out.println(us.getUser());
            }
        }

        return "user/courseSubTeacherPage";
    }

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public String Profile(Model model, Principal principal) throws ClassNotFoundException, SQLException {

        //102 103 don work
        //AppUser user= new AppUser();
        String name = principal.getName();
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                System.out.println(us.getUser());
            }
        }
        for (AppUser use : ur.getAll()) {
            if (use.getUserName().equals(name)) {
                model.addAttribute("user", use);
                AppUser user = use;
                for (Teacher teacher : tr.getAll()) {
                    if (teacher.getUser() == use) {
                        model.addAttribute("teacher", teacher);
                    }
                }
                model.addAttribute("userRole", use.getRoles());
            }
        }

        //  model.addAttriute("students",std.getAll());
        return "user/userProfilePage";
    }

    @RequestMapping(value = "/saveTeacher", method = RequestMethod.POST)
    public String save(@ModelAttribute("teacher") Teacher teacher, Model model, Principal principal, @RequestParam("teacher_name") String teacher_name,
            @RequestParam("teacher_email") String teacher_email,
            @RequestParam("teacher_contact") BigInteger teacher_contact,
            @RequestParam("teacher_type") String teacher_type,
            @RequestParam("status") Boolean status) {
        //  teacher.setTeacherPicture("");
        //  teacher.setTeacherWorkingHr(null);
        String name = principal.getName();
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                if(tr.findByEmail(us.getUser().getEmail())==null){
                tr.save(new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, status, us.getImage(), us.getUser()));
                } else{
                     tr.save(new Teacher(tr.findByEmail(us.getUser().getEmail()).getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, status, us.getImage(), us.getUser()));
               
                 }
                  
            }
        }
        //  model.addAttriute("students",std.getAll());
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                System.out.println(us.getUser());
            }
        }

        //tr.save( new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, null, status,null));
        return "redirect:/user/userProfile";
    }

    @RequestMapping(value = "/saveTeacher/{id}", method = RequestMethod.POST)
    public String save(@PathVariable("id") long id, Model model, Principal principal, @RequestParam("teacher_name") String teacher_name,
            @RequestParam("teacher_email") String teacher_email,
            @RequestParam("teacher_contact") BigInteger teacher_contact,
            @RequestParam("teacher_type") String teacher_type,
            @RequestParam("status") Boolean status) {
        //  teacher.setTeacherPicture("");
        //  teacher.setTeacherWorkingHr(null);
        String name = principal.getName();
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                tr.save(new Teacher(id, teacher_name, teacher_email, teacher_contact, teacher_type, status, us.getImage(), us.getUser()));
                // System.out.println(us.getUser());
            }
        }
        //  model.addAttriute("students",std.getAll());
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                System.out.println(us.getUser());
            }
        }

        //tr.save( new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, null, status,null));
        return "redirect:/user/userProfile";
    }

    @RequestMapping(value = "/saveUser/{id}", method = RequestMethod.POST)
    public String saveUser(@PathVariable("id") long id, Principal principal, Model model, @RequestParam("username") String username,
            @RequestParam("useremail") String email, @RequestParam("password") String password,
            @RequestParam("fullname") String fullname) {
        //  teacher.setTeacherPicture("");
        //  teacher.setTeacherWorkingHr(null);
        ur.save(new AppUser(id, username, fullname, email, encoder.encode((CharSequence) password), true, ur.getById(id).getRoles()));
        //  model.addAttriute("students",std.getAll());
        String name = principal.getName();
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                System.out.println(us.getUser());
            }
        }

        //tr.save( new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, null, status,null));
        return "redirect:/user/userProfile";
    }

    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public String saveStd(Model model, Principal principal, @RequestParam("student_name") String sname,
            @RequestParam("student_email") String semail,
            @RequestParam("student_contact") BigInteger scontact,
            @RequestParam("student_address") String saddress,
            @RequestParam("student_batch") Integer sbatch, @RequestParam("room") String sec,
            @RequestParam("course") Long course, @RequestParam("roll") Integer roll) {
        Student std = new Student();
        System.out.println(stdr.lastId().getStudentId());

        std.setStudentId(stdr.lastId().getStudentId() + 1);
        std.setStudentName(sname);
        std.setStudentEmail(semail);
        std.setStdcon(scontact);
        std.setStdbatch(sbatch);
        std.setStdadd(saddress);
        std.setRollno(roll);
        std.setSection(sec);
        for (AppUser us : ur.getAll()) {
            if (us.getEmail().equals(semail)) {
         //   tr.save(new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, status,us.getImage(),us.getUser()));
                //   stdr.save(new Student(stdr.lastId().getStudentId() + 1, sname, semail, roll, scontact, semail, sbatch, us.getUser(), null,null ));
                std.setUser(us);
                System.out.println(us.getFullName());
            }
        }
        for (Courses c : cr.getAll()) {
            if (c.getCoursesId().equals(course)) {
         //   tr.save(new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, status,us.getImage(),us.getUser()));
                //   stdr.save(new Student(stdr.lastId().getStudentId() + 1, sname, semail, roll, scontact, semail, sbatch, us.getUser(), null,null ));
                std.setCourse(c);
            }
            

            }
       
        stdr.save(std);

        // System.out.println(std);
        //tr.save( new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, null, status,null));
        model.addAttribute("successMessage", "You are registered successfully");
        return "loginPage";
    }

    
     @RequestMapping(value = "/semRoutine/{id}", method = RequestMethod.GET)
    public String semRoutine(@PathVariable("id") int id,Model model,Principal principal) {
        table1.clear();
        table2.clear();
     String name= principal.getName();
 for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
     if(id==1){
      model.addAttribute("course","I-I");
            timetable=ttr.findByCourse("I-I");
     }
     
      if(id==2){
      model.addAttribute("course","I-II");
            timetable=ttr.findByCourse("I-II");
     }
       if(id==3){
      model.addAttribute("course","II-I");
            timetable=ttr.findByCourse("II-I");
     }
        if(id==4){
      model.addAttribute("course","II-II");
            timetable=ttr.findByCourse("II-II");
     } if(id==5){
      model.addAttribute("course","III-I");
            timetable=ttr.findByCourse("III-I");
     }
      if(id==6){
      model.addAttribute("course","III-II");
            timetable=ttr.findByCourse("III-II");
     }
            Collections.sort(timetable);
            int length= timetable.size();
              System.out.println(timetable.get(0));
         for(int i=0;i<length;i++){
               if(i<5){
                 table1.add(timetable.get(i));
               }
               else{
                table2.add(timetable.get(i));
               }
               
             }
              System.out.println(table1);
             model.addAttribute("one",table1); 
             model.addAttribute("roomone",table1.get(2).getRoomno());
            
                model.addAttribute("two",table2); 
                model.addAttribute("roomtwo",table2.get(2).getRoomno());
                
              
        return "user/semRoutinePage";
    }
@RequestMapping(value = "/semRoutine", method = RequestMethod.GET)
    public String semoldRoutine(Model model,Principal principal) {
        
     String name= principal.getName();
 for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
              
        return "user/semOldRoutinePage";
    }

    @RequestMapping(value = "/saveTeachers", method = RequestMethod.POST)
    public String saveteach(Model model,Principal principal,@RequestParam("teacher_name") String teacher_name,
            @RequestParam("teacher_email") String teacher_email,
            @RequestParam("teacher_contact") BigInteger teacher_contact,
            @RequestParam("teacher_type") String teacher_type,
            @RequestParam("status") Boolean status) {
         //  teacher.setTeacherPicture("");
        //  teacher.setTeacherWorkingHr(null);
        Teacher teacher= new Teacher();
          for (AppUser us : ur.getAll()) {
            if (us.getEmail().equals(teacher_email)) {
         //   tr.save(new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, status,us.getImage(),us.getUser()));
                //   stdr.save(new Student(stdr.lastId().getStudentId() + 1, sname, semail, roll, scontact, semail, sbatch, us.getUser(), null,null ));
                teacher.setTeacherId(tr.findByEmail(us.getEmail()).getTeacherId());
            teacher.setTeacherName(teacher_name);
            teacher.setTeacherEmail(teacher_email);
            teacher.setTeacherContact(teacher_contact);
            teacher.setTeacherType(teacher_type);
            teacher.setUser(us);
            
            teacher.setStatus(status);
                System.out.println(teacher);
               tr.save(teacher);
            }
         
          }
      //  model.addAttriute("students",std.getAll());
       
       model.addAttribute("successMessage", "You are registered successfully");
        return "loginPage";
    }

    
}
