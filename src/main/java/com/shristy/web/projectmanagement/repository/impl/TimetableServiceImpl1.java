/*
 * Timetableo change this license header, choose License Headers in Project Properties.
 * Timetableo change this template file, choose Timetableools | Timetableemplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;

import com.shristy.web.projectmanagement.entity.Timetable;
import com.shristy.web.projectmanagement.entity.Timetablenext;

import com.shristy.web.projectmanagement.repository.TimetableRepository;
import com.shristy.web.projectmanagement.repository.TimetableRepository1;


import com.shristy.web.projectmanagement.service.GenericService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public class TimetableServiceImpl1 implements GenericService<Timetablenext> {

    @Autowired
    private TimetableRepository1 gr;
    private static Connection conn = null;

//    @Cacheable(value = "timetable")
    @Override
    public List<Timetablenext> getAll() {
        return gr.findAll();
    }

    @Override
    public Timetablenext getById(Long id) {
        return (Timetablenext) gr.getOne(id);

    }


//
//    @CacheEvict(allEntries = true, value = "timetable")
    @Override
    public void save(Timetablenext model) {

        gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
        Timetablenext entity = getById(id);
        if (entity != null) {
            gr.delete(entity);
            return true;
        }

        return false;
    }
//
//    @Cacheable(value = "timetable")
    public List<Timetablenext> findByRoomno(String roomNo) {
        return gr.findByRoomno(roomNo);
    }
//-----------------------------------------------for static save method(since all static ni GA Entity)----------------------------------------
//    @CacheEvict(allEntries = true, value = "timetable")
    public static void saveStatic(Timetablenext model,String courseName) throws SQLException, ClassNotFoundException {
 String sql="INSERT INTO timetablenext(\n" +
                    "id, seven15eight05, eight05eight55, eight55nine45, nine45ten35, ten35eleven25, eleven5twelve15, twelve15one05, one5one50, one50two45, two45three35, days, roomno,course_name)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
      pushpull(sql, new Object[]{
        model.getId(),model.getSeven15eight05(),model.getEight05eight55(),
            model.getEight55nine45(),model.getNine45ten35(),model.getTen35eleven25(),
            model.getEleven5twelve15(),model.getTwelve15one05(),model.getOne5one50(),model.getOne50two45(),model.getTwo45three35(),model.getDays(),model.getRoomno(),courseName
        });
    }

    private static void connection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost/logini";//cmj18006 is database name
        String user = "postgres";
        String pass = "power";
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(url, user, pass);

    }
    public static void updateStatic(Timetable model) throws SQLException, ClassNotFoundException {
 String sql="Update timetablenext SET seven15eight05=?, eight05eight55=?, eight55nine45=?, nine45ten35=?, ten35eleven25=?, eleven5twelve15=?, twelve15one05=?, one5one50=?, one50two45=?, two45three35=?, days=? where id=?";
      pushpull(sql, new Object[]{
        model.getSeven15eight05(),model.getEight05eight55(),
            model.getEight55nine45(),model.getNine45ten35(),model.getTen35eleven25(),
            model.getEleven5twelve15(),model.getTwelve15one05(),model.getOne5one50(),model.getOne50two45(),model.getTwo45three35(),model.getDays(),model.getId()
        });
    }

    private static void ssetParameters(Object[] params, PreparedStatement stm) throws SQLException {
        int counter = 1;
        for (Object para : params) {
            stm.setObject(counter, para);
            counter++;
        }

    }

    public static void pushpull(String sql, Object[] args) throws SQLException, ClassNotFoundException {
        connection();
        PreparedStatement stm
                = conn.prepareStatement(sql);
        ssetParameters(args, stm);
     
         int result=stm.executeUpdate();
         System.out.println("result"+ result);
        conn.close();
       
    }
       public static boolean get(String sql) throws SQLException, ClassNotFoundException {
        connection();
        PreparedStatement stm
                = conn.prepareStatement(sql);
        
         ResultSet rs=stm.executeQuery();
         if(rs!=null){
                  conn.close();
             return true;
          
         }
         
        conn.close();
       return false;
    }
         public static boolean checkExist() throws ClassNotFoundException, SQLException {
String sql = "select * from timetablenext where id=1";

        if(get(sql)){
            return true;
        }
        return false;
        }
         
         public static void truncate() throws ClassNotFoundException, SQLException{
             String sql = "TRUNCATE TABLE timetablenext";
              connection();
        PreparedStatement stm
                = conn.prepareStatement(sql);
        
        stm.executeUpdate();
         }
            public void update(Timetable model) throws SQLException, ClassNotFoundException {
 String sql="Update timetablenext set seven15eight05=model.getSeven15eight05(), eight05eight55=model.getEight05eight55(), eight55nine45= model.getEight55nine45(), nine45ten35=model.getNine45ten35(), ten35eleven25=model.getTen35eleven25(), eleven5twelve15= model.getEleven5twelve15(), twelve15one05=model.getTwelve15one05(), one5one50=model.getOne5one50(), one50two45=model.getOne50two45(), two45three35=model.getTwo45three35(), days=model.getDays() where id=model.getId()";
    connection();
        PreparedStatement stm
                = conn.prepareStatement(sql);
          int result=stm.executeUpdate();
         System.out.println("result"+ result);
        conn.close();
       
//      pushpull(sql, new Object[]{
//        model.getSeven15eight05(),model.getEight05eight55(),
//            model.getEight55nine45(),model.getNine45ten35(),model.getTen35eleven25(),
//            model.getEleven5twelve15(),model.getTwelve15one05(),model.getOne5one50(),model.getOne50two45(),model.getTwo45three35(),model.getDays(),model.getId()
//        });
    }
}
