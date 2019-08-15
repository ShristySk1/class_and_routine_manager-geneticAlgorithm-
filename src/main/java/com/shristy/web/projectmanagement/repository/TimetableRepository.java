/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository;

import com.shristy.web.projectmanagement.entity.Timetable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    @Query(value="select * from timetable t where t.roomno=?1",nativeQuery=true)
    public List<Timetable> findByRoomno(String roomNo);
     @Query(value="select * from timetable t where t.course_name=?1",nativeQuery=true)
    public List<Timetable> findByCourse(String course);
     @Modifying
 @Query(value="Update timetable t SET t.seven15eight05=?1, t.eight05eight55=?2, t.eight55nine45=?3, t.nine45ten35=?4, t.ten35eleven25=?5, t.eleven5twelve15=?6, t.twelve15one05=?7, t.one5one50=?8, t.one50two45=?9, t.two45three35=?10, t.days=?11 where t.id=?12",nativeQuery=true)
    public void postByRoomno(String seven15eight05,String eight05eight55,String eight55nine45,String nine45ten35,String ten35eleven25,String eleven5twelve15,String twelve15one05, String one5one50,String one50two45,String two45three35,String days, Long id);
    @Query(value="delete from timetable t where t.roomno=?1",nativeQuery=true)
    public void post(String rommNo);
}