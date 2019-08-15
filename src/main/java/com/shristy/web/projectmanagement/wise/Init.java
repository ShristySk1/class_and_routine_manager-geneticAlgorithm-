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
import com.shristy.web.projectmanagement.entity.FormValue;
import com.shristy.web.projectmanagement.entity.Room;
import com.shristy.web.projectmanagement.entity.Subjects;
import com.shristy.web.projectmanagement.entity.Teacher;
import com.shristy.web.projectmanagement.entity.UserImage;
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
@RequestMapping(value = {"/admin/download"}, method = RequestMethod.GET)

public class Init extends RoutineType {

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

    @GetMapping
    public String downloadPage(Model model,Principal principal) throws IOException, SQLException {

// readInput();
//        model.addAttribute("message", "Your Routine has been successfully downloaded");
        model.addAttribute("sem", cr.getAll());
        model.addAttribute("room", rr.getAll());
        model.addAttribute("formvalue", new FormValue());
        String name= principal.getName();
        for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
             System.out.println(us.getUser());
          }
         }
        return "init";
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

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public String generate(@ModelAttribute("formvalue") FormValue form,Model model,Principal principal) throws IOException, SQLException {
        ArrayList<ClassRoom> classroom = new ArrayList<>();
//List<Long> courseid=form.getsemId();
//    System.out.println("roomid: "+courseid);
        String name= principal.getName();
         for(UserImage us: ui.getAll() ){
          if(us.getUser().getUserName().equals(name)){
             model.addAttribute("image",us);
             System.out.println(us.getUser());
          }
         }
        List<Long> room_id = form.getRoomId();

        List<Room> rooms = rrr.findByRoomIdIn(room_id);

        for (Room rm : rooms) {

            System.out.println("room_is same : " + rm.getRoomValue());
            ClassRoom room = new ClassRoom(rm.getRoomValue(), 20, false, rm.getRoomValue());
            classroom.add(room);

        }

        for (Teacher teacher : tr.getAll()) {
            List<String> subjectlist = new ArrayList<>();
            System.out.println("teacher --teacherid" + teacher.getTeacherName() + teacher.getTeacherId());

            for (String subject : srr.onetomanyTeacherSubject(teacher.getTeacherId())) {

                System.out.println("subjects" + subject);
                subjectlist.add(subject);
            }
            System.out.println("out of subjectlist loop");
            professors.add(new Professor(teacher.getTeacherId(), teacher.getTeacherName(), subjectlist));
        }
   
////        
        System.out.println("create lecture-----------");
        createLectures(professors);

        TimeTable timetb1 = new TimeTable(classroom, classes);//, professors);

        List<String> listsub = new ArrayList<>();
        Long courseid = cr.getById(new Long(1)).getCoursesId();
        String courseName = cr.getById(new Long(1)).getCoursesName();
        System.out.println("reading input.......");
        int iterate_value = form.getSemRoomNo();
//            List<String> list_department_value=new ArrayList<>();
//                        for(char o=1;o<=iterate_value;o++){
//            list_department_value.add("BCT-"+courseid+"-"+(char)(o+64));
//                    }
//    System.out.println("list value----: "+list_department_value);
        for (Subjects sub : srr.getSubjectSem(courseid)) {
            System.out.println("subject name :" + sub.getSubjectName());
            System.out.println("subject perio pwer week :" + sub.getPeriodPerWeek());
            for (int t = 0; t < iterate_value; t++) {

                subjects.add(new Subject(sub.getSubjectId(), sub.getSubjectName(), sub.getPeriodPerWeek(), false, rooms.get(t).getRoomValue()));
                System.out.println("iterate subjecrt----" + rooms.get(t).getRoomValue());

            }

//            subjects.add(new Subject(sub.getSubjectId(), sub.getSubjectName(), sub.getPeriodPerWeek(), false,"BCT-1-B"));
            listsub.add(sub.getSubjectName());
        }

        System.out.println("new course creation.......");

        ////////////////////////////////////////////////////////
        Course course1 = new Course(courseid, courseName, subjects);
        System.out.println("courseNamein admin" + courseName);
        course1.createCombination(listsub, 20);

////////////////////////////////////////////////////////////
        course1.createStudentGroups();
        ArrayList<StudentGroups> studentGroups = course1.getStudentGroups();
        timetb1.addStudentGroups(studentGroups);

        subjects.clear();
//
////        /////////////////////////////////////////////////<sbjects+yearwise2>////////////////
        List<String> listsub2 = new ArrayList<>();
        Long courseid2 = cr.getById(new Long(2)).getCoursesId();
        String courseName2 = cr.getById(new Long(2)).getCoursesName();
        System.out.println("reading input.......");
//        list_department_value.clear();
//                        for(char o=1;o<=iterate_value;o++){
//            list_department_value.add("BCT-"+courseid2+"-"+(char)(o+64));
//                    }

        for (Subjects sub : srr.getSubjectSem(courseid2)) {
            for (int t = 0; t < iterate_value; t++) {
                subjects.add(new Subject(sub.getSubjectId(), sub.getSubjectName(), sub.getPeriodPerWeek(), false, rooms.get(iterate_value + t).getRoomValue()));
                System.out.println("iyterate course2-- " + rooms.get(iterate_value + t).getRoomValue());
            }
            listsub2.add(sub.getSubjectName());
        }

        for (Subject ss : subjects) {
            System.out.println("subjects bhitra2 :" + ss.getNumberOfLecturesPerWeek());
        }

        Course course2 = new Course(courseid2, courseName2, subjects);
        System.out.println("courseName2in admin" + courseName2);
        course2.createCombination(listsub2, 20);

        course2.createStudentGroups();
        studentGroups = course2.getStudentGroups();
        timetb1.addStudentGroups(studentGroups);

        System.out.println("Setting tt.......");

        System.out.println("adding tt.......");
        timetb1.initializeTimeTable();

        timetables.add(timetb1);

        System.out.println("populating.......");

        //display();
        populateTimeTable(timetb1);
        GeneticAlgorithm ge = new GeneticAlgorithm();

        try {
            //ge.fitness(timetb1);
//		timetb1.createTimeTableGroups(combinations);
            ge.populationAccepter(timetables);
//		//ge.fitness(timetb2);

//ge.fitness(timetb3);
//populateTimeTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/admin";
    }

    @Override
    public void readInput() throws IOException, SQLException {

    }
}
