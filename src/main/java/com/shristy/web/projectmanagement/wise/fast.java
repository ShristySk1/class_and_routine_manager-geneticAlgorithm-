/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.wise;

import com.shristy.web.projectmanagement.entity.ga.ClassRoom;
import com.shristy.web.projectmanagement.entity.ga.Professor;
import com.shristy.web.projectmanagement.entity.ga.GeneticAlgorithm;
import com.shristy.web.projectmanagement.entity.ga.Lecture;
import com.shristy.web.projectmanagement.entity.ga.Day;
import com.shristy.web.projectmanagement.entity.ga.Course;
import com.shristy.web.projectmanagement.entity.ga.Combination;
import com.shristy.web.projectmanagement.entity.ga.TimeSlot;
import com.shristy.web.projectmanagement.entity.ga.StudentGroups;
import com.shristy.web.projectmanagement.entity.ga.TimeTable;
import com.shristy.web.projectmanagement.entity.ga.Subject;
import com.shristy.web.projectmanagement.RoutineType;
import com.shristy.web.projectmanagement.entity.FormSubject;
import com.shristy.web.projectmanagement.entity.FormValue;
import com.shristy.web.projectmanagement.entity.FormValue2;
import com.shristy.web.projectmanagement.entity.Room;
import com.shristy.web.projectmanagement.entity.Subjects;
import com.shristy.web.projectmanagement.entity.TeachSub;
import com.shristy.web.projectmanagement.entity.Teacher;
import com.shristy.web.projectmanagement.entity.UserImage;
import com.shristy.web.projectmanagement.entity.ga.ga2.Initialization;
import com.shristy.web.projectmanagement.repository.RoomRepository;
import com.shristy.web.projectmanagement.repository.SubjectsRepository;
import com.shristy.web.projectmanagement.repository.TeacherRepository;
import com.shristy.web.projectmanagement.repository.impl.CoursesServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.RoomServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.SubjectsServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TeacherServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TimetableServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.UserImageServiceImpl;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"/admin/fast"}, method = RequestMethod.GET)

public class fast extends RoutineType {

    //this class takes all inputs from a file. courseID, courseName, roomID's, subjects and professors associated with course
    //currently hardcoded by taking one course with 6 subjects and 6 teachers
    @Autowired
    private TeacherServiceImpl tr;
       @Autowired
    private UserImageServiceImpl ui;
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
    //this class takes all inputs from a file. courseID, courseName, roomID's, subjects and professors associated with course
    //currently hardcoded by taking one course with 6 subjects and 6 teachers
    private ArrayList<Subject> subjects = new ArrayList();
    private ArrayList<Professor> professors = new ArrayList();
    private ArrayList<TimeTable> timetables = new ArrayList();
    private ArrayList<Lecture> classes = new ArrayList<>();
    private ArrayList<Combination> combinations = new ArrayList<>();

    @GetMapping
    public String downloadPage(Model model,Principal principal) throws IOException, SQLException {
FormValue2 two=new FormValue2();
      
     
        for (int i = 1; i <= 5; i++) {
        two.addTeachSub(new TeachSub());
        two.addSub(new FormSubject());
    }
        
            model.addAttribute("form", two); 
              String name= principal.getName();
        for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
             System.out.println(us.getUser());
          }
         }

        return "fast";
    }

//
    private void createLectures(ArrayList<Professor> professors) {
        // TODO Auto-generated method stub

        java.util.Iterator<Professor> professorIterator = professors.iterator();
        while (professorIterator.hasNext()) {
            Professor professor = professorIterator.next();
            ArrayList<String> subjectsTaught = professor.getSubjectTaught();
            Iterator<String> subjectIterator = subjectsTaught.iterator();
            while (subjectIterator.hasNext()) {
                String subject = subjectIterator.next();
                classes.add(new Lecture(professor, subject));
                System.out.println("professor and subject" + professor.getProfessorName() + subject);
            }
        }
    }

    public void populateTimeTable(TimeTable timetb1) {
        int i = 0;
        System.out.println("populating started.......");
        while (i < 3) {
            TimeTable tempTimetable = timetb1;
            ArrayList<ClassRoom> allrooms = tempTimetable.getRoom();
            Iterator<ClassRoom> allroomsIterator = allrooms.iterator();
            while (allroomsIterator.hasNext()) {
                ClassRoom room = allroomsIterator.next();
                ArrayList<Day> weekdays = room.getWeek().getWeekDays();
                Collections.shuffle(weekdays);
                if (!room.isLaboratory()) {
                    Iterator<Day> daysIterator = weekdays.iterator();
                    while (daysIterator.hasNext()) {
                        Day day = daysIterator.next();
                        Collections.shuffle(day.getTimeSlot());
                    }
                }
            }
            timetables.add(tempTimetable);
            i++;
        }
        System.out.println("populating done.......");
        System.out.println("display called.......");
        display();
    }
    //displays all timetables

    private void display() {
        // TODO Auto-generated method stub
        int i = 1;
        System.out.println("displaying all tt's.......");
        Iterator<TimeTable> timetableIterator = timetables.iterator();
        while (timetableIterator.hasNext()) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++\nTime Table no. " + i);
            TimeTable currentTimetable = timetableIterator.next();
            System.out.println("Score : " + currentTimetable.getFittness());
            ArrayList<ClassRoom> allrooms = currentTimetable.getRoom();
            Iterator<ClassRoom> allroomsIterator = allrooms.iterator();
            while (allroomsIterator.hasNext()) {
                ClassRoom room = allroomsIterator.next();
                System.out.println("Room: " + room.getRoomNo());
                ArrayList<Day> weekdays = room.getWeek().getWeekDays();
                Iterator<Day> daysIterator = weekdays.iterator();
                while (daysIterator.hasNext()) {
                    Day day = daysIterator.next();
                    ArrayList<TimeSlot> timeslots = day.getTimeSlot();
                    Iterator<TimeSlot> timeslotIterator = timeslots.iterator();
                    //System.out.print(""+day.getName()+": ");
                    while (timeslotIterator.hasNext()) {
                        TimeSlot lecture = (TimeSlot) timeslotIterator.next();
                        if (lecture.getLecture() != null) {
//                            System.out.print(" (Subject: "+lecture.getLecture().getSubject()+" --> Professor: "+lecture.getLecture().getProfessor().getProfessorName()+" GrpName: "+lecture.getLecture().getStudentGroup().getName()+")");
                            System.out.print("(" + lecture.getLecture().getSubject() + "#" + lecture.getLecture().getProfessor().getProfessorName() + "#" + lecture.getLecture().getStudentGroup().getName().split("/")[0] + ")");
                        } else {
                            System.out.print("   free   ");
                        }
                    }
                    System.out.print("\n");
                }
                System.out.print("\n\n");
            }
            i++;
        }
    }

    @RequestMapping(value = "/faster", method = RequestMethod.POST)
    public String generate(@ModelAttribute("form") FormValue2 form) throws IOException, SQLException, ClassNotFoundException {
//	ArrayList<ClassRoom> classroom=new ArrayList<>();
//		ClassRoom room1 = new ClassRoom("D101", 20, false, "Common");
//		classroom.add(room1);
//////  System.out.println("---------------hghjghghgjhbhj"+srr.isitLab(true));
//int i=1;
//for(TeachSub ts: form.getTeachsubs()){
//                professors.add(new Professor(new Long(i), ts.getTeachername(), ts.getSubject()));
//i++;
//}
//
//
////        for (Teacher teacher : tr.getAll()) {
////            List<String> subjectlist = new ArrayList<>();
////            System.out.println("teacher --teacherid" + teacher.getTeacherName() + teacher.getTeacherId());
////
////            for (String subject : srr.onetomanyTeacherSubject(teacher.getTeacherId())) {
//////                   Boolean islab = srr.lab(subject);
//////                   if(islab){
//////                       String newSubject= subject+"lab";
//////                        subjectlist.add(newSubject);
//////                   }
////                System.out.println("subjects" + subject);
////                subjectlist.add(subject);
////               
////            }
////            System.out.println("out of subjectlist loop");
////            professors.add(new Professor(teacher.getTeacherId(), teacher.getTeacherName(), subjectlist));
////        }
////   
////////        
////        System.out.println("create lecture-----------");
//        createLectures(professors);
////
//        TimeTable timetb1 = new TimeTable(classroom, classes);//, professors);
////
//        List<String> listsub = new ArrayList<>();
//        Long courseid = new Long(1);
//        String courseName = form.getRoutinename();
////        System.out.println("reading input.......");
////        int iterate_value = form.getSemRoomNo();
//////            List<String> list_department_value=new ArrayList<>();
//////                        for(char o=1;o<=iterate_value;o++){
//////            list_department_value.add("BCT-"+courseid+"-"+(char)(o+64));
//////                    }
//////    System.out.println("list value----: "+list_department_value);
////        for (Subjects sub : srr.getSubjectSem(courseid)) {
////            System.out.println("subject name :" + sub.getSubjectName());
////            System.out.println("subject perio pwer week :" + sub.getPeriodPerWeek());
////            for (int t = 0; t < iterate_value; t++) {
//int o=1;
//for(FormSubject fs: form.getFormsubjects()){
//    listsub.add(fs.getSub());
//                    subjects.add(new Subject(new Long(o),fs.getSub(),fs.getClassperweek(), false, "computer"));
//o++;
//}
//
////                System.out.println("iterate subjecrt----" + rooms.get(t).getRoomValue());
////
////            }
////
//////            subjects.add(new Subject(sub.getSubjectId(), sub.getSubjectName(), sub.getPeriodPerWeek(), false,"BCT-1-B"));
////            listsub.add(sub.getSubjectName());
////        }
////
//        System.out.println("new course creation.......");
////
////        ////////////////////////////////////////////////////////
//        Course course1 = new Course(courseid, courseName, subjects);
////        System.out.println("courseNamein admin" + courseName);
//        course1.createCombination(listsub, 20);
////
////////////////////////////////////////////////////////////////
//        course1.createStudentGroups();
//        ArrayList<StudentGroups> studentGroups = course1.getStudentGroups();
//        timetb1.addStudentGroups(studentGroups);
////
//        subjects.clear();
//////
////////        /////////////////////////////////////////////////<sbjects+yearwise2>////////////////
////       
////
//        System.out.println("Setting tt.......");
//
//        System.out.println("adding tt.......");
//
//
//        timetb1.initializeTimeTable();
//
//        timetables.add(timetb1);
//
//        System.out.println("populating.......");
//
//        //display();
//        populateTimeTable(timetb1);
//        GeneticAlgorithm1 ge = new GeneticAlgorithm1();
//
//        try {
//            //ge.fitness(timetb1);
////		timetb1.createTimeTableGroups(combinations);
//List<String> ll=srr.isitLab(true);
//            ge.populationAccepter(timetables,ll);
////		//ge.fitness(timetb2);
//
////ge.fitness(timetb3);
////populateTimeTable();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(fast.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(fast.class.getName()).log(Level.SEVERE, null, ex);
//        }
        Initialization initialize=new Initialization();       
	        initialize.readInput(form.getTeachsubs(),form.getRoutinename(),form.getFormsubjects());
return "redirect:/admin/newroutine";
    }

    @Override
    public void readInput() throws IOException, SQLException {

    }
}
