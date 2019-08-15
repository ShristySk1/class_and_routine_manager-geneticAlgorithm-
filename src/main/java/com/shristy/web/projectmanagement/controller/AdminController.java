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
import com.shristy.web.projectmanagement.entity.Student;
import com.shristy.web.projectmanagement.entity.Subjects;
import com.shristy.web.projectmanagement.entity.Teacher;
import com.shristy.web.projectmanagement.entity.TeacherSubject;
import com.shristy.web.projectmanagement.entity.Timetable;
import com.shristy.web.projectmanagement.entity.TimetableForm;
import com.shristy.web.projectmanagement.entity.TimetableForm1;
import com.shristy.web.projectmanagement.entity.Timetablenext;
import com.shristy.web.projectmanagement.entity.Todolist;
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
import com.shristy.web.projectmanagement.repository.TimetableRepository1;
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
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Principal;
import java.sql.SQLException;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;


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

import java.util.Date;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    //this class takes all inputs from a file. courseID, courseName, roomID's, subjects and professors associated with course
    //currently hardcoded by taking one course with 6 subjects and 6 teachers
    @Autowired
    private TeacherServiceImpl tr;
    @Autowired
    BCryptPasswordEncoder encoder;
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
     @Autowired
    private StudentServiceImpl str;
      @Autowired
    private AppUserServiceImpl ur;
        @Autowired
    private MessageServiceImpl mr;
              
              
     
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
   private ArrayList<Teacher> teach= new ArrayList<>();
   


    @Autowired
    private TeacherSubjectServiceImpl tsr;
@Autowired
private TodolistServiceImpl todo;
    @Autowired
    private TimetableServiceImpl ttrimpl;
    @Autowired
    private TimetableRepository ttr;
          @Autowired
    private TimetableRepository1 ttrr;
private TimetableForm tform;
    private Subjects subject = new Subjects();
    private Teacher teacher = new Teacher();

    @GetMapping
    public String adminPage(Model model, Principal principal) {
        teach.clear();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        for(int i=0;i<11;i++){
            teach.add(tr.getAll().get(i));
        }
         List<Courses> lists = cr.getAll();
        Collections.sort(lists);

         model.addAttribute("semester",lists);
        model.addAttribute("todolist",todo.getAll());

        model.addAttribute("teachers", teach);
        model.addAttribute("teachcount",tr.getAll().size());
        model.addAttribute("name", principal.getName());
        String name= principal.getName();
        model.addAttribute("cocount",cr.getAll().size());
       model.addAttribute("subcount",sr.getAll().size());
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
         
         
        return "admin/adminPage";
//        return "adminPage";
    }
 @PostMapping(value = ("/savenotice"))
public String noticePost(@ModelAttribute("Todolist") Todolist todolist,Model model,Principal principal){
Date date=new Date();
     Timestamp ts = new Timestamp(date.getTime());
     todolist.setCreatedDate(ts);
todo.save(todolist);
 String name= principal.getName();
 for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
    
    return "redirect:/admin";
}

    @RequestMapping(value = "/routine", method = RequestMethod.GET)
    public String RoutinePage(Model model,Principal principal) throws ClassNotFoundException, SQLException {
List<Timetable> listtimetable=new ArrayList<>();

listtimetable=ttr.findByRoomno("RoomNo(101)");
Collections.sort(listtimetable);
        model.addAttribute("one",new TimetableForm(listtimetable));
        //102 103 don work
listtimetable=ttr.findByRoomno("RoomNo(104)");
Collections.sort(listtimetable);
        model.addAttribute("one104",new TimetableForm(listtimetable));
        
        listtimetable=ttr.findByRoomno("RoomNo(201)");
Collections.sort(listtimetable);
        model.addAttribute("one201",new TimetableForm(listtimetable));
        listtimetable=ttr.findByRoomno("RoomNo(202)");
Collections.sort(listtimetable);
        model.addAttribute("one202",new TimetableForm(listtimetable));
        listtimetable=ttr.findByRoomno("RoomNo(103)");
Collections.sort(listtimetable);
        model.addAttribute("one103",new TimetableForm(listtimetable));
        listtimetable=ttr.findByRoomno("RoomNo(105)");
Collections.sort(listtimetable);
        model.addAttribute("one105",new TimetableForm(listtimetable));
        
//        model.addAttribute("two201", ttr.findByRoomno("RoomNo(201)"));
//        model.addAttribute("two202", ttr.findByRoomno("RoomNo(202)"));

//         model.addAttribute("one103",ttr.findByRoomno("RoomNo(103)"));
         
         
//         model.addAttribute("one105",ttr.findByRoomno("RoomNo(105)"));
//        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhelo-----------------"+ttr.findByRoomno("RoomNo(105)"));
       String name= principal.getName();
 for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
    
        return "admin/routinePage";
    }
@RequestMapping(value = "/newroutine", method = RequestMethod.GET)
    public String newRoutinePage(Model model,Principal principal) throws ClassNotFoundException, SQLException {
List<Timetablenext> listtimetable=new ArrayList<>();

listtimetable=ttrr.findByRoomno("D001");
Collections.sort(listtimetable);
        model.addAttribute("one",new TimetableForm1(listtimetable));
       
//    
//listtimetable=ttrr.findByRoomno("E101");
//Collections.sort(listtimetable);
//        model.addAttribute("two",new TimetableForm1(listtimetable));    
       String name= principal.getName();
 for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
         return "admin/new";
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String teachers(Model model,Principal principal) {
        List<Teacher> teach= tr.getAll();
        Collections.sort(teach);
        model.addAttribute("teachers",teach);
String name= principal.getName();
 for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
    
        return "admin/teachers/teachersPage";
    }

    @RequestMapping(value = "/addteacher", method = RequestMethod.GET)
    public String addteacher() {

        return "admin/teachers/teachersPage";
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
                
              
        return "admin/semRoutinePage";
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
              
        return "admin/semOldRoutinePage";
    }

    @GetMapping(value = "/profileId/{id}")//params = "id")
     public String profileId(@PathVariable("id") long id,Model model,Principal principal){
   // public String profileId(@RequestParam long id, Model model) {

        model.addAttribute("oneTeacher", tr.getById((long) id));
        String name= principal.getName();
 for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
    
        return "admin/userprofile/profileIdPage";
    }

    @GetMapping(value = "/teachers/add")
    public String add(Model model,Principal principal) {
        model.addAttribute("courses",cr.getAll());
         String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
        return "admin/teachers/AddTeacher";
    }
    
    @GetMapping(value = "/teachers/addSpec/{id}")
    public String addSpec(@PathVariable("id") long id,Model model,Principal principal) {
        
       model.addAttribute("subject",sr.getById(id));
      //model.addAttribute("department",cr.getById(id).getDepartmentId());
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
        return "admin/teachers/AddSpecTeacher";
    }

    @GetMapping(value = "/teachers/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model,Principal principal) {
        model.addAttribute("teacher", tr.getById(id));
         String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
        return "admin/teachers/editTeacher";
    }

    @GetMapping(value = "/teachers/delete/{id}/{sid}")
    public String delete(@PathVariable("id") long id,@PathVariable("id") long sid) {

      
          for( TeacherSubject tes:tr.getById(id).getTeacherSubjectList()){
             tsr.delete(tes.getTsId());
          } 
            tr.delete(id);
        return "redirect:/admin/courses/courseSubTeacher/{sid}";
    }
    
     @GetMapping(value = "/teachers/delete")
    public String delete(Model model,Principal principal ) {
       model.addAttribute("teachers",tr.getAll());
        //tr.delete(id);
       String name= principal.getName();
 for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
    
        return "admin/teachers/deleteTeacher";
    }
    
     @GetMapping(value = "/student/delete")
    public String deletestd(Model model,Principal principal ) {
       model.addAttribute("student",str.getAll());
        //tr.delete(id);
       String name= principal.getName();
 for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
              model.addAttribute("name", us.getUser().getFullName());
             System.out.println(us.getUser());
          }
         }
    
        return "admin/deleteStudent";
    }
    
    /* @PostMapping(value =("/teachers/sureDelete"))
    public String SureDelete( @RequestParam("teacherName") Collection<String> teacherName){
       for(String tid: teacherName ){
              for(Teacher teach: tr.getAll()){
                 if(teach.getTeacherName().equals(tid)){
                   tr.delete(teach.getTeacherId());
                 }
              }
       }
          return "redirect:/admin/teachers";
    }*/

     @PostMapping(value = ("/student/sureDelete"))
    public String SureDelete (@RequestParam("studentId") Long stdId,Principal principal ){
            str.delete(stdId);
             String name= principal.getName();
 for(AppUser us: ur.getAll() ){
          if(us.equals(name)){
             
             ur.delete(us.getUserId());
          }
         }
    
          return "redirect:/admin/student";
    }


    /**
     *
     * @param id
     * @param teacher_name
     * @param teacher_email
     * @param teacher_contact
     * @param teacher_type
     * @param status
     *
     * @return
     */
    @RequestMapping(value = "/teachers/save/{id}", method = RequestMethod.POST)

    public String save(@PathVariable("id") long id, @RequestParam("teacher_name") String teacher_name,
            @RequestParam("teacher_email") String teacher_email,
            @RequestParam("teacher_contact") BigInteger teacher_contact,
            @RequestParam("teacher_type") String teacher_type,
            @RequestParam("status") Boolean status) {
         //  teacher.setTeacherPicture("");
        //  teacher.setTeacherWorkingHr(null);

        tr.save(new Teacher(id, teacher_name, teacher_email, teacher_contact, teacher_type, status));
        return "redirect:/admin/teachers";
    }
    
    /**
     *
     * @param teacher
     * @param id
     * @param teacher_name
     * @param teacher_email
     * @param teacher_contact
     * @param teacher_type
     * @param status
     *
     * @return
     */
    @RequestMapping(value = "/teachers/saveCourse/{id}", method = RequestMethod.POST)

    public String saveCourse(@ModelAttribute("teacher") Teacher teacher,@PathVariable("id") long id, @RequestParam("teacher_name") String teacher_name,
            @RequestParam("teacher_email") String teacher_email,
            @RequestParam("teacher_contact") BigInteger teacher_contact,
            @RequestParam("teacher_type") String teacher_type,
            @RequestParam("status") Boolean status) {
         
       //
         Long sid = null;
      // List< TeacherSubject> teachsub= tsr.getAll();
       
       TeacherSubject tes = new TeacherSubject();
          Teacher teach=new Teacher(teacher.getTeacherId(),teacher_name, teacher_email, teacher_contact, teacher_type, status);
       
       sid=tsr.lastId().getTsId()+ 1;
      // System.out.println(sid);
        tr.save(teach);
       tsr.save(new TeacherSubject(sid,sr.getById(id),teach));
      //  tr.save(teach);
      

        return "redirect:/admin/courses/courseSubTeacher/{id}";
    }

    /**
     *
     * @param teacher
     * @param teacher_name
     * @param teacher_email
     * @param teacher_contact
     * @param teacher_type
     * @param status
     *
     * @return
     */
    @RequestMapping(value = "/teachers/save", method = RequestMethod.POST)

    public String save(@ModelAttribute("teacher") Teacher teacher, @RequestParam("teacher_name") String teacher_name,
            @RequestParam("teacher_email") String teacher_email,
            @RequestParam("teacher_contact") BigInteger teacher_contact,
            @RequestParam("teacher_type") String teacher_type,
            @RequestParam("status") Boolean status) {
         //  teacher.setTeacherPicture("");
        //  teacher.setTeacherWorkingHr(null);
        tr.save(new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, status));
        //tr.save( new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, null, status,null));
        return "redirect:/admin/teachers";
    }

    

   /*@RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public String subjects(Model model) {

        model.addAttribute("Subjects", sr.getAll());

        return "admin/subjects/subjectsPage";
    }*/

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String std(Model model,Principal principal) {
        model.addAttribute("student", str.getAll());
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
             System.out.println(us.getUser());
          }
         }
        return "admin/studentPage";
    }

       @GetMapping(value = "/student/add")
    public String addS(Model model,Principal principal) {
       // model.addAttribute("courses",cr.getAll());
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
             System.out.println(us.getUser());
          }
         }
        return "admin/AddStudent";
    }
    
     @RequestMapping(value = "/student/save", method = RequestMethod.POST)

    public String saveS(@ModelAttribute("student") Student std, @RequestParam("student_name") String std_name,
            @RequestParam("student_email") String email,
            @RequestParam("roll") Integer roll) {
         //  teacher.setTeacherPicture("");
        //  teacher.setTeacherWorkingHr(null);
        //tr.save(new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, status));
        //tr.save( new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, null, status,null));
       Student st = new Student();
       st.setStudentId((long)1);
       st.setStudentName(std_name);
       st.setStudentEmail(email);
       st.setRollno(roll);
       str.save(st);
        return "redirect:/admin/student";
    }
     @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public String Profile(Model model, Principal principal) throws ClassNotFoundException, SQLException {

        //102 103 don work
        //AppUser user= new AppUser();
        String name = principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
             System.out.println(us.getUser());
          }
         }
        for (AppUser use : ur.getAll()) {
            if (use.getUserName().equals(name)) {
                model.addAttribute("user", use);
              //  AppUser user =use;
                for (Teacher teacher : tr.getAll()) {
                    if (teacher.getUser()== use) {
                        model.addAttribute("teacher", teacher);
                    }
                }
                model.addAttribute("userRole", use.getRoles());
            }
        }
       
        
      //  model.addAttriute("students",std.getAll());
        return "admin/userProfilePage";
    }

    
    @RequestMapping(value = "/uploadPhoto",  method = RequestMethod.POST)

     
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
        
        // D:\classes\project_third\manager-test\src\main\resources\static\img
             FileOutputStream os = new FileOutputStream("e:/classes/project_edit/manager-test/src/main/resources/static/img/"+ principal.getName()+"."+ cont);
              String src="/img/"+ principal.getName()+"."+ cont;
              byte[] data = new byte[1024 * 20];
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
          }else
               img.setImgId(ui.lastId().getImgId()+1);
         }
        for (AppUser us : ur.getAll()) {
            if (us.getUserName().equals(name)) {
               
                img.setUser(us);
                img.setImage(src);
                 System.out.println(us);
            }
        }
      
            ui.save(img);
     return "redirect:/admin/userProfile";
    }
    @RequestMapping(value = "/saveTeacher", method = RequestMethod.POST)
    public String save(@ModelAttribute("teacher") Teacher teacher,Model model,Principal principal,@RequestParam("teacher_name") String teacher_name,
            @RequestParam("teacher_email") String teacher_email,
            @RequestParam("teacher_contact") BigInteger teacher_contact,
            @RequestParam("teacher_type") String teacher_type,
            @RequestParam("status") Boolean status) {
         //  teacher.setTeacherPicture("");
        //  teacher.setTeacherWorkingHr(null);
          String name = principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
            tr.save(new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, status,us.getImage(),us.getUser()));
             // System.out.println(us.getUser());
          }
        }
    
      //  model.addAttriute("students",std.getAll());
        for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
             System.out.println(us.getImage());
          }
         }
        return "redirect:/admin/userProfile";
    }
    
     @RequestMapping(value = "/saveTeacher/{id}", method = RequestMethod.POST)
    public String save(@PathVariable("id") long id,Model model,Principal principal,@RequestParam("teacher_name") String teacher_name,
            @RequestParam("teacher_email") String teacher_email,
            @RequestParam("teacher_contact") BigInteger teacher_contact,
            @RequestParam("teacher_type") String teacher_type,
            @RequestParam("status") Boolean status) {
         //  teacher.setTeacherPicture("");
        //  teacher.setTeacherWorkingHr(null);
          String name = principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
            tr.save(new Teacher(id, teacher_name, teacher_email, teacher_contact, teacher_type, status,us.getImage(),us.getUser()));
             // System.out.println(us.getUser());
          }
        }
      //  model.addAttriute("students",std.getAll());
        for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
             System.out.println(us.getUser());
          }
         }
    
        
        //tr.save( new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, null, status,null));
        return "redirect:/admin/userProfile";
    }

     @RequestMapping(value = "/saveUser/{id}", method = RequestMethod.POST)
    public String saveUser(@PathVariable("id") long id,Principal principal, Model model,@RequestParam("username") String username,
            @RequestParam("useremail") String email,@RequestParam("password") String password,
            @RequestParam("fullname") String fullname) {
         //  teacher.setTeacherPicture("");
        //  teacher.setTeacherWorkingHr(null);
         ur.save(new AppUser(id, username, fullname, email,encoder.encode((CharSequence) password),true,ur.getById(id).getRoles()));
      //  model.addAttriute("students",std.getAll());
       String name= principal.getName();
     for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
             System.out.println(us.getUser());
          }
         }
        
        //tr.save( new Teacher(teacher.getTeacherId(), teacher_name, teacher_email, teacher_contact, teacher_type, null, status,null));
        return "redirect:/admin/userProfile";
    }

}
