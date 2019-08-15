/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;

import com.shristy.web.projectmanagement.entity.Feedback;
import com.shristy.web.projectmanagement.repository.FeedbackRepository;
import com.shristy.web.projectmanagement.service.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class FeedbackServiceImpl implements GenericService<Feedback>{

    @Autowired
    private FeedbackRepository fr;
    
    @Override
    public List<Feedback> getAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return fr.findAll();
    }

    @Override
    public Feedback getById(Long id) {
        return fr.getOne(id);
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Feedback model) {
        fr.save(model);
    }

    @Override
    public boolean delete(Long id) {
        Feedback feedback=getById(id);
        if (feedback!=null){
        fr.delete(feedback);
        return true;
        }
        return false;
    }
    
    public List<Feedback> test() {
        return fr.select(1);
    }

    public Iterable<Feedback> save(List<Feedback> feedback) {
       return fr.saveAll(feedback);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
