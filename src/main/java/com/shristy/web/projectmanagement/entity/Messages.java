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
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
 
@Entity
@Table(name = "\"messages\"")
public class Messages implements Serializable,Comparable<Messages> {
    
    
     
    @Id
    
    @GeneratedValue
    @Column(name = "\"messageId\"", nullable = false)
    private Long messageId;
 
    @Column(name = "\"messageText\"", nullable = false)
    private String msgTxt;
    
     @JoinColumn(name = "\"userid\"")
    @ManyToOne(optional = false)
   
    private AppUser user;
     
      @JoinColumn(name = "\"fromId\"")
    @ManyToOne(optional = false)
   
    private AppUser fromuser;
      
       @JoinColumn(name = "\"toId\"")
    @ManyToOne(optional = false)
   
    private AppUser touser;

    public Messages() {
    }
       
       

    public Messages(Long messageId, String msgTxt, AppUser user, AppUser fromuser, AppUser touser) {
        this.messageId = messageId;
        this.msgTxt = msgTxt;
        this.user = user;
        this.fromuser = fromuser;
        this.touser = touser;
    }

    public Messages(Long messageId, String msgTxt) {
        this.messageId = messageId;
        this.msgTxt = msgTxt;
    }
       
       
       
       
    

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMsgTxt() {
        return msgTxt;
    }

    public void setMsgTxt(String msgTxt) {
        this.msgTxt = msgTxt;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public AppUser getFromuser() {
        return fromuser;
    }

    public void setFromuser(AppUser fromuser) {
        this.fromuser = fromuser;
    }

    public AppUser getTouser() {
        return touser;
    }

    public void setTouser(AppUser touser) {
        this.touser = touser;
    }

    @Override
    public String toString() {
        return "Messages{" + "messageId=" + messageId + ", msgTxt=" + msgTxt + ", user=" + user.toString() + ", fromuser=" + fromuser.toString() + ", touser=" + touser.toString() + '}';
    }

    @Override
    public int compareTo(Messages o) {
         if (getMessageId() == null || o.getMessageId() == null) {
      return 0;
    }
    return getMessageId().compareTo(o.getMessageId());
    }
 
    
     
}