/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

/**
 *
 * @author Dell
 */
public class Subjectsss {
    private String name;
       private String code;
    private Long cid;

    public Subjectsss() {
    }

    public Subjectsss(String name, String code, Long cid) {
        this.name = name;
        this.code = code;
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }
    
    

    
}
