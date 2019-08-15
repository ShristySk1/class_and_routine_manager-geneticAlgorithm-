/*
 * AppRoleo change this license header, choose License Headers in Project Properties.
 * AppRoleo change this template file, choose AppRoleools | AppRoleemplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;


import com.shristy.web.projectmanagement.entity.AppRole;
import com.shristy.web.projectmanagement.repository.AppRoleRepository;




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
public class AppRoleServiceImpl implements GenericService<AppRole>{
    @Autowired
private  AppRoleRepository gr;
 
//@Cacheable(value="teacher_subject")
    @Override
    public List<AppRole> getAll() {
return gr.findAll();

    }

    @Override
    public AppRole getById(Long id) {
return (AppRole)gr.getOne(id);
    }
    //@CacheEvict(allEntries=true,value="teacher_subject")
    @Override
    public void save(AppRole model) {
gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
     AppRole entity=getById(id);
        if(entity!=null){
            gr.delete(entity);
            return true;
        }
    
    return false;
    }
     
  public AppRole lastId(){
        AppRole teachSub= new AppRole();
    List<AppRole> ts =getAll();
    Collections.sort(ts);
    for(int i= 0; i < ts.size();i++){
       teachSub= ts.get(i);
    }
    
     
        return teachSub;
    
        
    
    }
  
}