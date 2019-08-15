/*
 * Subjectso change this license header, choose License Headers in Project Properties.
 * Subjectso change this template file, choose Subjectsools | Subjectsemplates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository.impl;

import com.shristy.web.projectmanagement.entity.Subjects;
import com.shristy.web.projectmanagement.repository.SubjectsRepository;

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
public class SubjectsServiceImpl implements GenericService<Subjects> {

    @Autowired
    private SubjectsRepository gr;

//@Cacheable(value="subject")
    @Override
    public List<Subjects> getAll() {
        return gr.findAll();
    }

    @Override
    public Subjects getById(Long id) {
        return (Subjects) gr.getOne(id);
    }
//    @CacheEvict(allEntries=true,value="subject")

    @Override
    public void save(Subjects model) {
        gr.save(model);
    }

    @Override
    public boolean delete(Long id) {
        Subjects entity = getById(id);
        if (entity != null) {
            gr.delete(entity);
            return true;
        }

        return false;
    }

    public Subjects lastId() {
        Subjects Sub = new Subjects();
        List<Subjects> ts = getAll();
        Collections.sort(ts);
        

        for (int i = 0; i < ts.size(); i++) {
            Sub = ts.get(i);
        }

        return Sub;

    }

}
