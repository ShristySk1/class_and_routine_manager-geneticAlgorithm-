/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository;



import com.shristy.web.projectmanagement.entity.TeacherSubject;
import com.shristy.web.projectmanagement.entity.Teacher;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**ssss
 *
 * @author Administrator
 */
@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject,Long>{
    @Query(value="select * from teacher_subject t where t.teacher_id=?1",nativeQuery=true)
    public List<TeacherSubject> findByTeacher(Teacher teacher);
}
