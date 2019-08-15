/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

/**
 *
 * @author Administrator
 */
public class Teach {
    private Long id;
    private String teachername;

    public Teach(Long id, String teachername) {
        this.id = id;
        this.teachername = teachername;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getTeachername() {
        return teachername;
    }

    public Long getId() {
        return id;
    }
    
}
