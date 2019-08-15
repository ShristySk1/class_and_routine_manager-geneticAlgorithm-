/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository;

import com.shristy.web.projectmanagement.entity.Subjects;
import com.shristy.web.projectmanagement.entity.Teacher;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface SubjectsRepository extends JpaRepository<Subjects,Long>{
  @Query(value="select s.subject_name from subject s,teacher_subject ts where ts.subject_id=s.subject_id and ts.teacher_id=?1",nativeQuery=true) 
  public List<String> onetomanyTeacherSubject(Long id);
  
  
  
  @Query(value="select s.subject_name,s.subject_id,s.period_per_week,s.course_id,s.department_id,s.is_lab,s.subject_code from subject s where course_id=?1",nativeQuery=true)
     public List<Subjects> getSubjectSem(Long id);
      @Query(value="select s.subject_name,s.subject_id,s.period_per_week,s.course_id,s.department_id,s.is_lab,s.subject_code from subject s where course_id=?1",nativeQuery=true)
     public Subjects subjectSem(Long id);
//     
//     @Query(value = "select * from subject where course_id=?1")
//     public List<Subjects> getsubjectlist(Long courseid);
     
}
