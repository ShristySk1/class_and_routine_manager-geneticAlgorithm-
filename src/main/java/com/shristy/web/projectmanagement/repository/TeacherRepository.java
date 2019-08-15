/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository;

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
public interface TeacherRepository extends JpaRepository<Teacher,Long>{
  
    @Query(value="select * from teacher where teacher_id=?1", nativeQuery = true)
    List<Teacher> select(Integer id);
     @Query(value="select * from teacher t where t.teacher_email=?1",nativeQuery=true)
    public Teacher findByEmail(String email);
}
