/*
 * UserImageo change this license header, choose License Headers in Project Properties.
 * UserImageo change this template file, choose UserImageools | UserImageemplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;


import com.shristy.web.projectmanagement.entity.UserImage;
import com.shristy.web.projectmanagement.repository.UserImageRepository;




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
public class UserImageServiceImpl implements GenericService<UserImage>{
    @Autowired
private  UserImageRepository gr;
 
//@Cacheable(value="teacher_subject")
    @Override
    public List<UserImage> getAll() {
return gr.findAll();

    }

    @Override
    public UserImage getById(Long id) {
return (UserImage)gr.getOne(id);
    }
    //@CacheEvict(allEntries=true,value="teacher_subject")
    @Override
    public void save(UserImage model) {
gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
     UserImage entity=getById(id);
        if(entity!=null){
            gr.delete(entity);
            return true;
        }
    
    return false;
    }
     
 
      public UserImage lastId(){
        UserImage teachSub= new UserImage();
    List<UserImage> ts =getAll();
    Collections.sort(ts);
    for(int i= 0; i < ts.size();i++){
       teachSub= ts.get(i);
    }
    
     
        return teachSub;
    
        
    
    }
   
   
}
