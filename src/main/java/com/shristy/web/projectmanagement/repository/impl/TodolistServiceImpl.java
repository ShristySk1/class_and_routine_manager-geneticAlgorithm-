/*
 * Todolisto change this license header, choose License Headers in Project Properties.
 * Todolisto change this template file, choose Todolistools | Todolistemplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;




import com.shristy.web.projectmanagement.entity.Todolist;
import com.shristy.web.projectmanagement.entity.Todolist;
import com.shristy.web.projectmanagement.repository.TodolistRepository;

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
public class TodolistServiceImpl implements GenericService<Todolist>{
    @Autowired
private  TodolistRepository gr;
 
@Cacheable(value="todolist")
    @Override
    public List<Todolist> getAll() {
return gr.findAll();
    }

    @Override
    public Todolist getById(Long id) {
return (Todolist)gr.getOne(id);
    }
    @CacheEvict(allEntries=true,value="todolist")
    @Override
    public void save(Todolist model) {
gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
     Todolist entity=getById(id);
        if(entity!=null){
            gr.delete(entity);
            return true;
        }
    
    return false;
    }
    
}
