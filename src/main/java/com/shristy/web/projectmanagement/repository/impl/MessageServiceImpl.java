/*
 * Messageso change this license header, choose License Headers in Project Properties.
 * Messageso change this template file, choose Messagesools | Messagesemplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;


import com.shristy.web.projectmanagement.entity.Messages;
import com.shristy.web.projectmanagement.entity.AppUser;
import com.shristy.web.projectmanagement.repository.MessageRepository;




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
public class MessageServiceImpl implements GenericService<Messages>{
    @Autowired
private  MessageRepository gr;
 
//@Cacheable(value="teacher_subject")
    @Override
    public List<Messages> getAll() {
return gr.findAll();

    }

    @Override
    public Messages getById(Long id) {
return (Messages)gr.getOne(id);
    }
    //@CacheEvict(allEntries=true,value="teacher_subject")
    @Override
    public void save(Messages model) {
gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
     Messages entity=getById(id);
        if(entity!=null){
            gr.delete(entity);
            return true;
        }
    
    return false;
    }
     
 
      public Messages lastId(){
        Messages teachSub= new Messages();
    List<Messages> ts =getAll();
    Collections.sort(ts);
    for(int i= 0; i < ts.size();i++){
       teachSub= ts.get(i);
    }
    
     
        return teachSub;
    
        
    
    }
   
       public List<Messages> findByUser(AppUser user){
     return  gr.findByUser(user);
       
   }
   
}
