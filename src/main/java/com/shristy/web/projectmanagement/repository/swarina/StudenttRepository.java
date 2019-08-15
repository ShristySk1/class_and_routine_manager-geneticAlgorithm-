package com.shristy.web.projectmanagement.repository.swarina;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.shristy.web.projectmanagement.entity.swarina.Studentt;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface StudenttRepository extends JpaRepository<Studentt,Long>{
  
    @Query(value="select * from studentt where id=?1", nativeQuery = true)
    List<Studentt> select(Integer id);

//@Query(value="select s.student_name,s.student_rollno from student s",nativeQuery = true)
//   String specificselect();


}
