/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.service;

import com.shristy.web.projectmanagement.entity.Teacher;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 */
@Service
public interface GenericService<T> {
        List<T> getAll();
    T getById(Long id);
    void save(T model);
    boolean delete(Long id);
}
