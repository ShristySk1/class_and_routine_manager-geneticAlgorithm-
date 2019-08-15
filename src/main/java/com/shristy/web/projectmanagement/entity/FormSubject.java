/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class FormSubject {
    private String sub;
    private Integer classperweek;

    public FormSubject() {
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Integer getClassperweek() {
        return classperweek;
    }

    public void setClassperweek(Integer classperweek) {
        this.classperweek = classperweek;
    }
    
}
