/*
 * Teachertableo change this license header, choose License Headers in Project Properties.
 * Teachertableo change this template file, choose Teachertableools | Teachertableemplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;

import com.shristy.web.projectmanagement.entity.Teacher;
import com.shristy.web.projectmanagement.entity.TeacherSubject;
import com.shristy.web.projectmanagement.entity.Teachertable;

import com.shristy.web.projectmanagement.repository.TeachertableRepository;


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
public class TeachertableServiceImpl implements GenericService<Teachertable> {

    @Autowired
    private TeachertableRepository gr;
    private static Connection conn = null;

//    @Cacheable(value = "timetable")
    @Override
    public List<Teachertable> getAll() {
        return gr.findAll();
    }

    @Override
    public Teachertable getById(Long id) {
        return (Teachertable) gr.getOne(id);

    }


//
//    @CacheEvict(allEntries = true, value = "timetable")
    @Override
    public void save(Teachertable model) {

        gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
        Teachertable entity = getById(id);
        if (entity != null) {
            gr.delete(entity);
            return true;
        }

        return false;
    }
     public List<Teachertable> findByTeacher(Long teacher){
     return  gr.findByTeacher(teacher);
       
   }
     public List<Teachertable> getDayTable(String str,Long id){
     return  gr.findByDay(str,id);
       
   }
//
//    @Cacheable(value = "timetable")
   
//-----------------------------------------------for static save method(since all static ni GA Entity)----------------------------------------
//    @CacheEvict(allEntries = true, value = "timetable")
   

    private static void connection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost/logini";//cmj18006 is database name
        String user = "postgres";
        String pass = "power";
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(url, user, pass);

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
String sql = "select * from teachertable where id=1";

        if(get(sql)){
            return true;
        }
        return false;
        }
         
         public static void truncate() throws ClassNotFoundException, SQLException{
             String sql = "TRUNCATE TABLE teachertable";
              connection();
        PreparedStatement stm
                = conn.prepareStatement(sql);
        
        stm.executeUpdate();
         }
            
       
//      pushpull(sql, new Object[]{
//        model.getSeven15eight05(),model.getEight05eight55(),
//            model.getEight55nine45(),model.getNine45ten35(),model.getTen35eleven25(),
//            model.getEleven5twelve15(),model.getTwelve15one05(),model.getOne5one50(),model.getOne50two45(),model.getTwo45three35(),model.getDays(),model.getId()
//        });
    }

