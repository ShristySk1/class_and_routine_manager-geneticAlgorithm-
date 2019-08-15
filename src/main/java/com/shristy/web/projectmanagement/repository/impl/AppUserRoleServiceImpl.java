/*
 * AppUserRoleo change this license header, choose License Headers in Project Properties.
 * AppUserRoleo change this template file, choose AppUserRoleools | AppUserRoleemplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;

import com.shristy.web.projectmanagement.entity.AppUser;
import com.shristy.web.projectmanagement.entity.AppUserRole;
import com.shristy.web.projectmanagement.repository.AppUserRoleRepository;

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
public class AppUserRoleServiceImpl implements GenericService<AppUserRole> {

    @Autowired
    private AppUserRoleRepository gr;

//@Cacheable(value="teacher_subject")
    @Override
    public List<AppUserRole> getAll() {
        return gr.findAll();

    }

    @Override
    public AppUserRole getById(Long id) {
        return (AppUserRole) gr.getOne(id);
    }

    //@CacheEvict(allEntries=true,value="teacher_subject")

    @Override
    public void save(AppUserRole model) {
        gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
        AppUserRole entity = getById(id);
        if (entity != null) {
            gr.delete(entity);
            return true;
        }

        return false;
    }
    
     public long lastId(){
        AppUserRole user= new AppUserRole();
    List<AppUserRole> ts =getAll();
    Collections.sort(ts);
    for(int i= 0; i < ts.size();i++){
       user= ts.get(i);
    }
    
     
        return user.getUserRoleId();
    
        
    
    }

}
