/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository;

import com.shristy.web.projectmanagement.entity.Courses;
import com.shristy.web.projectmanagement.entity.Todolist;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**ssss
 *
 * @author Administrator
 */
@Repository
public interface TodolistRepository extends JpaRepository<Todolist,Long>{
     @Query(value="select * from todolist t where t.list_sem=?1",nativeQuery=true)
    public List<Todolist> findByCourse(String course);
        @Query(value="select * from todolist",nativeQuery=true)
    public List<Todolist> findByC();
}
