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
import com.shristy.web.projectmanagement.entity.Room;
import com.shristy.web.projectmanagement.entity.Subjects;
import com.shristy.web.projectmanagement.entity.Teacher;
import com.shristy.web.projectmanagement.entity.TeacherSubject;
import com.shristy.web.projectmanagement.entity.Teachertable;
import com.shristy.web.projectmanagement.entity.Timetable;
import com.shristy.web.projectmanagement.entity.TimetableForm;
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
import com.shristy.web.projectmanagement.repository.TodolistRepository;
import com.shristy.web.projectmanagement.repository.impl.AppUserRoleServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.AppUserServiceImpl;

import com.shristy.web.projectmanagement.repository.impl.CoursesServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.MessageServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.RoomServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.StudentServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.SubjectsServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeacherServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeacherSubjectServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TimetableServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TodolistServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.UserImageServiceImpl;
import com.shristy.web.projectmanagement.utils.WebUtils;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Principal;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
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
@RequestMapping(value = "/student")
public class StudentController {

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
    private TodolistRepository tdr;
    
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
    private ArrayList<Messages> messages= new ArrayList<>();
     private List<Teachertable> teachertable;
     String room1,room2;

    @Autowired
    private TeacherSubjectServiceImpl tsr;

    @Autowired
    private TimetableServiceImpl ttrimpl;
    
     @Autowired
    private MessageServiceImpl mr;
    
    @Autowired
    private TimetableRepository ttr;
    
     @Autowired
    private TodolistServiceImpl todo;

    @Autowired
    private AppUserRepository urep;
    
    @Autowired
    private UserImageServiceImpl ui;

    private Subjects subject = new Subjects();
    private Teacher teacher = new Teacher();
    private Student std = new Student();

    @GetMapping
    public String studentPage(Model model, Principal principal) {
      
     table1.clear();
     table2.clear();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
       // System.out.println(loginedUser);
       
         String daysArray[] = {"Sunday","Monday","Tuesday", "Wednesday","Thursday","Friday", "Saturday"};

            Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_WEEK);
      List<Courses> lists = cr.getAll();
        Collections.sort(lists);

         model.addAttribute("semester",lists);
         
      model.addAttribute("cocount",cr.getAll().size());
       model.addAttribute("subcount",sr.getAll().size());
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
                messages=(ArrayList<Messages>) mr.findByUser(us.getUser());
              model.addAttribute("count",messages.size());
               model.addAttribute("msges",messages);
               model.addAttribute("cuser",us.getUser());
             System.out.println(us.getUser());
          }
         }
       
         for(Student s: stdr.getAll() ){
          if(s.getUser().getUserName().equals(name)){
             model.addAttribute("today",daysArray[day-1]);
            model.addAttribute("course",s.getCourse().getCoursesName());
             model.addAttribute("todolist",tdr.findByCourse(s.getCourse().getCoursesName()));
             model.addAttribute("section",s.getSection());
            timetable=ttr.findByCourse(s.getCourse().getCoursesName());
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
              if(s.getSection()=="A"){
             model.addAttribute("one",table1); 
             model.addAttribute("roomone",table1.get(2).getRoomno());
           
              }else{
            
                model.addAttribute("one",table2); 
                model.addAttribute("roomone",table2.get(2).getRoomno());
              }
            
          }
         }
         // model.addAttribute("image","/img/avatar7.png");
        return "user/student/studentUserPage";
//        return "adminPage";
    }
    
     @RequestMapping(value = "/routine", method = RequestMethod.GET)
    public String semRoutine(Model model,Principal principal) {
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
 
 for(Student s: stdr.getAll() ){
          if(s.getUser().getUserName().equals(name)){
            
            model.addAttribute("course",s.getCourse().getCoursesName());
            timetable=ttr.findByCourse(s.getCourse().getCoursesName());
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
            
          }
         }
    


        
                 
      
        
        return "user/student/RoutinePage";
    }
    
     @RequestMapping(value = "/studentUserProfile", method = RequestMethod.GET)
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
                for (Student student : stdr.getAll()) {
                    if (student.getUser() == use) {
                        model.addAttribute("student", student);
                        model.addAttribute("course", student.getCourse().getCoursesName());
                    }
                }
                model.addAttribute("userRole", use.getRoles());
            }
        }

        //  model.addAttriute("students",std.getAll());
        return "user/student/userStdProfilePage";
    }
    
     @RequestMapping(value = "/saveStudent/{id}", method = RequestMethod.POST)
    public String saveStd(@PathVariable("id") long id,Model model, Principal principal, @RequestParam("student_name") String sname,
            @RequestParam("student_email") String semail,
            @RequestParam("student_contact") BigInteger scontact,
            @RequestParam("student_address") String saddress,
            @RequestParam("student_batch") Integer sbatch, @RequestParam("room") String sec,
            @RequestParam("course") Long course, @RequestParam("roll") Integer roll) {
        Student std = new Student();
       // System.out.println(stdr.lastId().getStudentId());

        std.setStudentId(id);
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
        return "redirect:/student/studentUserProfile";
    }
 @RequestMapping(value = "/uploadServlet",  method = RequestMethod.POST)
//@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
//public class UploadController extends HttpServlet {
     
    // database connection settings
  
     
    public String doPost(HttpServletRequest request,
            HttpServletResponse response,Principal principal,Model model) throws ServletException, IOException {
        
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
             System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        String content=filePart.getContentType();
       String cont = "";
        int j=0;
        for(int i =content.indexOf("/")+ 1; i <content.length();i++){
             cont+=content.charAt(i);
             j++;
             }
        
         //   D:\classes\project_third\manager-test\src\main\resources\static\img
              FileOutputStream os = new FileOutputStream("e:/classes/project_edit/manager-test/src/main/resources/static/img/"+ principal.getName()+"."+ cont);
              String src="/img/"+ principal.getName()+"."+ cont;
              byte[] data = new byte[1024 * 10];
            int i = 0;
           if (inputStream != null) {
                while((i=inputStream.read(data)) != -1) {
                os.write(data, 0, i);
           }
            os.close();
          inputStream.close();
        } else {
            System.out.println("Not enough parameters");
       }
                UserImage img = new UserImage();
             String name = principal.getName();
               for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
             img.setImgId(us.getImgId());
             System.out.println(us.getUser());
          }else{
           img.setImgId((ui.lastId().getImgId()+1));
          }
         }
               
        for (AppUser us : ur.getAll()) {
            if (us.getUserName().equals(name)) {
                
                img.setUser(us);
                img.setImage(src);
                 System.out.println(us);
            }
        }
      
            ui.save(img);
     return "redirect:/student/studentUserProfile";
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
        return "user/student/teachersPage";
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
                 model.addAttribute("name", us.getUser().getFullName());
                System.out.println(us.getUser());
            }
        }

        return "user/subjectsPage";
    }
    
     @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String coursePage(Model model, Principal principal) throws ClassNotFoundException, SQLException {

        //102 103 don work
        List<Courses> listcr = cr.getAll();
        Collections.sort(listcr);
        model.addAttribute("Courses", listcr);
       
        String name = principal.getName();
        for (UserImage us : ui.getAll()) {
            if (us.getUser().getUserName().equals(name)) {
                model.addAttribute("image", us);
                model.addAttribute("name", us.getUser().getFullName());
                System.out.println(us.getUser());
            }
        }

        return "user/student/coursesPage";
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
                model.addAttribute("name", us.getUser().getFullName());
                System.out.println(us.getUser());
            }
        }
        return "user/student/courseSubjectPage";
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
                model.addAttribute("name", us.getUser().getFullName());
                System.out.println(us.getUser());
            }
        }

        return "user/student/courseSubTeacherPage";
    }

}