/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository;

import com.shristy.web.projectmanagement.entity.Courses;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**ssss
 *
 * @author Administrator
 */
@Repository
public interface CoursesRepository extends JpaRepository<Courses,Long>{
    @Query(value="select c.course_id from course c",nativeQuery=true)
   List<Long> findRespectiveIds();
}
