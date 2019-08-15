/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;

import com.shristy.web.projectmanagement.entity.AppUser;
import com.shristy.web.projectmanagement.entity.Student;
import com.shristy.web.projectmanagement.repository.StudentRepository;
import com.shristy.web.projectmanagement.service.GenericService;
import java.util.Collections;
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
public class StudentServiceImpl implements GenericService<Student>{
@Autowired
private StudentRepository tr;
@Autowired
private AppUserServiceImpl aur;

//@Cacheable(value="teacher")
    @Override
    public List<Student> getAll()
    {
        List<Student> ts = tr.findAll();
        Collections.sort(ts);
        
        return ts;
        
    }

    @Override
    public Student getById(Long id) {
    return tr.getOne(id);
    }

//    @CacheEvict(allEntries=true,value="teacher")
    @Override
    public void save(Student model) {
tr.save(model);
    }

    @Override
    public boolean delete(Long id) {
        Student teacher=getById(id);
        if(teacher!=null){
            tr.delete(teacher);
            return true;
        }
    
    return false;
    }
    
    
   public Student lastId() {
        Student Sub = new Student();
        List<Student> ts = getAll();
        Collections.sort(ts);
        

        for (int i = 0; i < ts.size(); i++) {
            Sub = ts.get(i);
        }

        return Sub;

    }
   
   

    
}
