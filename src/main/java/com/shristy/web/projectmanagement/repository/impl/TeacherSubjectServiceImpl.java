/*
 * TeacherSubjecto change this license header, choose License Headers in Project Properties.
 * TeacherSubjecto change this template file, choose TeacherSubjectools | TeacherSubjectemplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;


import com.shristy.web.projectmanagement.entity.TeacherSubject;
import com.shristy.web.projectmanagement.entity.Teacher;
import com.shristy.web.projectmanagement.repository.TeacherSubjectRepository;



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
public class TeacherSubjectServiceImpl implements GenericService<TeacherSubject>{
    @Autowired
private  TeacherSubjectRepository gr;
 
//@Cacheable(value="teacher_subject")
    @Override
    public List<TeacherSubject> getAll() {
return gr.findAll();

    }

    @Override
    public TeacherSubject getById(Long id) {
return (TeacherSubject)gr.getOne(id);
    }
    //@CacheEvict(allEntries=true,value="teacher_subject")
    @Override
    public void save(TeacherSubject model) {
gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
     TeacherSubject entity=getById(id);
        if(entity!=null){
            gr.delete(entity);
            return true;
        }
    
    return false;
    }
     
    public TeacherSubject lastId(){
        TeacherSubject teachSub= new TeacherSubject();
    List<TeacherSubject> ts =getAll();
    Collections.sort(ts);
    for(int i= 0; i < ts.size();i++){
       teachSub= ts.get(i);
    }
    
     
        return teachSub;
    
        
    
    }
    
   public List<TeacherSubject> getlecture(Teacher teacher){
     return  gr.findByTeacher(teacher);
       
   }
}
