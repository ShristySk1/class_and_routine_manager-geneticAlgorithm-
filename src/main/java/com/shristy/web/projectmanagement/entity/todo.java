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
public class todo {
    private String notice;

    public todo() {
    }

    public todo(String notice) {
        this.notice = notice;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
    
}
