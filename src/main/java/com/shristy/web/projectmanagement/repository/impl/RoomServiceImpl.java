/*
 * Roomo change this license header, choose License Headers in Project Properties.
 * Roomo change this template file, choose Roomools | Roomemplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;


import com.shristy.web.projectmanagement.entity.Room;

import com.shristy.web.projectmanagement.repository.RoomRepository;
import static com.shristy.web.projectmanagement.repository.impl.TimetableServiceImpl.get;

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
public class RoomServiceImpl implements GenericService<Room>{
    @Autowired
private  RoomRepository gr;
     private static Connection conn = null;


    public RoomServiceImpl() {
    }
 
//@Cacheable(value="room")
    @Override
    public List<Room> getAll() {
return gr.findAll();
    }

    @Override
    public Room getById(Long id) {
return (Room)gr.getOne(id);
    }
//    @CacheEvict(allEntries=true,value="room")
    @Override
    public void save(Room model) {
gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
     Room entity=getById(id);
        if(entity!=null){
            gr.delete(entity);
            return true;
        }
    
    return false;
    }
    
     public Room findByRoom(String room){
     return  gr.findByRoom(room);
       
   }
      private static void connection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost/login2";//cmj18006 is database name
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
public static void findByRoomval() throws ClassNotFoundException, SQLException {
String sql = "select * from room where room_value=?1";
 connection();
        PreparedStatement stm
                = conn.prepareStatement(sql);
        
        stm.executeUpdate();
         conn.close();

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
    
}
