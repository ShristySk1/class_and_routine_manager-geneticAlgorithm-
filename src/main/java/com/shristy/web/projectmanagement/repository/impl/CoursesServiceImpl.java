/*
 * Courseso change this license header, choose License Headers in Project Properties.
 * Courseso change this template file, choose Coursesools | Coursesemplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;


import com.shristy.web.projectmanagement.entity.Courses;

import com.shristy.web.projectmanagement.repository.CoursesRepository;

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
public class CoursesServiceImpl implements GenericService<Courses>{
    @Autowired
private  CoursesRepository gr;
 
@Cacheable(value="course")
    @Override
    public List<Courses> getAll() {
return gr.findAll();
    }

    @Override
    public Courses getById(Long id) {
return (Courses)gr.getOne(id);
    }
    @CacheEvict(allEntries=true,value="course")
    @Override
    public void save(Courses model) {
gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
     Courses entity=getById(id);
        if(entity!=null){
            gr.delete(entity);
            return true;
        }
    
    return false;
    }
    
}
