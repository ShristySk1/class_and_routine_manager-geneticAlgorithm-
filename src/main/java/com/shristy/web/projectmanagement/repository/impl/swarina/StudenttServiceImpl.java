package com.shristy.web.projectmanagement.repository.impl.swarina;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.shristy.web.projectmanagement.entity.swarina.Studentt;
import com.shristy.web.projectmanagement.repository.swarina.StudenttRepository;
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
public class StudenttServiceImpl implements GenericService<Studentt>{
@Autowired
private StudenttRepository sr;

//@Cacheable(value="Student")
    @Override
    public List<Studentt> getAll() {
        return sr.findAll();
        
    }

    @Override
    public Studentt getById(Long id) {
    return sr.getOne(id);
    }

//    @CacheEvict(allEntries=true,value="Student")
    @Override
    public void save(Studentt model) {
sr.save(model);
    }

    @Override
    public boolean delete(Long id) {
        Studentt student=getById(id);
        if(student!=null){
            sr.delete(student);
            return true;
        }
    
    return false;
    }
     public List<Studentt> test() {
        return sr.select(1);
    }
    
    
}
