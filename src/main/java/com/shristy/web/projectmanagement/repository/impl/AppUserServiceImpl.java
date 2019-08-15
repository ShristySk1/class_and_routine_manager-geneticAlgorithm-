/*
 * AppUsero change this license header, choose License Headers in Project Properties.
 * AppUsero change this template file, choose AppUserools | AppUseremplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;


import com.shristy.web.projectmanagement.entity.AppUser;
import com.shristy.web.projectmanagement.repository.AppUserRepository;




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
public class AppUserServiceImpl implements GenericService<AppUser>{
    @Autowired
private  AppUserRepository gr;
 
//@Cacheable(value="teacher_subject")
    @Override
    public List<AppUser> getAll() {
return gr.findAll();

    }

    @Override
    public AppUser getById(Long id) {
return (AppUser)gr.getOne(id);
    }
    //@CacheEvict(allEntries=true,value="teacher_subject")
    @Override
    public void save(AppUser model) {
gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
     AppUser entity=getById(id);
        if(entity!=null){
            gr.delete(entity);
            return true;
        }
    
    return false;
    }
     
    public AppUser lastId(){
        AppUser teachSub= new AppUser();
    List<AppUser> ts =getAll();
    Collections.sort(ts);
    for(int i= 0; i < ts.size();i++){
       teachSub= ts.get(i);
    }
    
     
        return teachSub;
    
        
    
    }
  
}