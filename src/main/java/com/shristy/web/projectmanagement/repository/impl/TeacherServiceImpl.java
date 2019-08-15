/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;

import com.shristy.web.projectmanagement.entity.Teacher;
import com.shristy.web.projectmanagement.repository.TeacherRepository;
import com.shristy.web.projectmanagement.service.GenericService;
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
public class TeacherServiceImpl implements GenericService<Teacher>{
@Autowired
private TeacherRepository tr;

//@Cacheable(value="teacher")
    @Override
    public List<Teacher> getAll() {
        return tr.findAll();
        
    }

    @Override
    public Teacher getById(Long id) {
    return tr.getOne(id);
    }

//    @CacheEvict(allEntries=true,value="teacher")
    @Override
    public void save(Teacher model) {
tr.save(model);
    }

    @Override
    public boolean delete(Long id) {
        Teacher teacher=getById(id);
        if(teacher!=null){
            tr.delete(teacher);
            return true;
        }
    
    return false;
    }
     public List<Teacher> test() {
        return tr.select(1);
    }
    
      public Teacher findByEmail(String email){
     return  tr.findByEmail(email);
       
   }
}
