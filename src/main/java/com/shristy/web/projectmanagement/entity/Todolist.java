/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "todolist")
@NamedQueries({
    @NamedQuery(name = "Todolist.findAll", query = "SELECT t FROM Todolist t")})
public class Todolist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "list_id")
    private Integer listId;
    @Size(max = 2147483647)
    @Column(name = "list_notice")
    private String listNotice;
    @Size(max = 10)
    @Column(name = "list_sem")
    private String listSem;
    @Size(max = 50)
    @Column(name = "list_title")
    private String listTitle;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Todolist() {
    }

    public Todolist(String listNotice) {
        this.listNotice = listNotice;
    }

    public Todolist(Integer listId) {
        this.listId = listId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getListNotice() {
        return listNotice;
    }

    public void setListNotice(String listNotice) {
        this.listNotice = listNotice;
    }

    public String getListSem() {
        return listSem;
    }

    public void setListSem(String listSem) {
        this.listSem = listSem;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public String getCreatedDate() throws ParseException {
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
     String dates= dateFormat.format(createdDate);
//       SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//       Date date = sdf.parse(dates);
        return dates;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listId != null ? listId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Todolist)) {
            return false;
        }
        Todolist other = (Todolist) object;
        if ((this.listId == null && other.listId != null) || (this.listId != null && !this.listId.equals(other.listId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shristy.web.projectmanagement.entity.Todolist[ listId=" + listId + " ]";
    }
    
}
