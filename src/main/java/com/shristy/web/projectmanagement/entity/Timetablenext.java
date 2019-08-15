/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "timetablenext")

public class Timetablenext implements Serializable,Comparable<Timetablenext> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "seven15eight05")
    private String seven15eight05;
    @Size(max = 100)
    @Column(name = "eight05eight55")
    private String eight05eight55;
    @Size(max = 100)
    @Column(name = "eight55nine45")
    private String eight55nine45;
    @Size(max = 100)
    @Column(name = "nine45ten35")
    private String nine45ten35;
    @Size(max = 100)
    @Column(name = "ten35eleven25")
    private String ten35eleven25;
    @Size(max = 100)
    @Column(name = "eleven5twelve15")
    private String eleven5twelve15;
    @Size(max = 100)
    @Column(name = "twelve15one05")
    private String twelve15one05;
    @Size(max = 100)
    @Column(name = "one5one50")
    private String one5one50;
    @Size(max = 100)
    @Column(name = "one50two45")
    private String one50two45;
    @Size(max = 100)
    @Column(name = "two45three35")
    private String two45three35;
    @Size(max = 15)
    @Column(name = "days")
    private String days;
    @Size(max = 20)
    @Column(name = "roomno")
    private String roomno;

    public Timetablenext(Long id, String seven15eight05, String eight05eight55, String eight55nine45, String nine45ten35, String ten35eleven25, String eleven5twelve15, String twelve15one05, String one5one50, String one50two45, String two45three35, String days, String roomno) {
        this.id = id;
        this.seven15eight05 = seven15eight05;
        this.eight05eight55 = eight05eight55;
        this.eight55nine45 = eight55nine45;
        this.nine45ten35 = nine45ten35;
        this.ten35eleven25 = ten35eleven25;
        this.eleven5twelve15 = eleven5twelve15;
        this.twelve15one05 = twelve15one05;
        this.one5one50 = one5one50;
        this.one50two45 = one50two45;
        this.two45three35 = two45three35;
        this.days = days;
        this.roomno = roomno;
    }

    public Timetablenext() {
    }

    public Timetablenext(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeven15eight05() {
        return seven15eight05;
    }

    public void setSeven15eight05(String seven15eight05) {
        this.seven15eight05 = seven15eight05;
    }

    public String getEight05eight55() {
        return eight05eight55;
    }

    public void setEight05eight55(String eight05eight55) {
        this.eight05eight55 = eight05eight55;
    }

    public String getEight55nine45() {
        return eight55nine45;
    }

    public void setEight55nine45(String eight55nine45) {
        this.eight55nine45 = eight55nine45;
    }

    public String getNine45ten35() {
        return nine45ten35;
    }

    public void setNine45ten35(String nine45ten35) {
        this.nine45ten35 = nine45ten35;
    }

    public String getTen35eleven25() {
        return ten35eleven25;
    }

    public void setTen35eleven25(String ten35eleven25) {
        this.ten35eleven25 = ten35eleven25;
    }

    public String getEleven5twelve15() {
        return eleven5twelve15;
    }

    public void setEleven5twelve15(String eleven5twelve15) {
        this.eleven5twelve15 = eleven5twelve15;
    }

    public String getTwelve15one05() {
        return twelve15one05;
    }

    public void setTwelve15one05(String twelve15one05) {
        this.twelve15one05 = twelve15one05;
    }

    public String getOne5one50() {
        return one5one50;
    }

    public void setOne5one50(String one5one50) {
        this.one5one50 = one5one50;
    }

    public String getOne50two45() {
        return one50two45;
    }

    public void setOne50two45(String one50two45) {
        this.one50two45 = one50two45;
    }

    public String getTwo45three35() {
        return two45three35;
    }

    public void setTwo45three35(String two45three35) {
        this.two45three35 = two45three35;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    @Override
    public String toString() {
        return "Timetable{" + "id=" + id + ", seven15eight05=" + seven15eight05 + ", eight05eight55=" + eight05eight55 + ", eight55nine45=" + eight55nine45 + ", nine45ten35=" + nine45ten35 + ", ten35eleven25=" + ten35eleven25 + ", eleven5twelve15=" + eleven5twelve15 + ", twelve15one05=" + twelve15one05 + ", one5one50=" + one5one50 + ", one50two45=" + one50two45 + ", two45three35=" + two45three35 + ", days=" + days + ", roomno=" + roomno + '}';
    }

      @Override
    public int compareTo(Timetablenext o) {
         if (getId() == null || o.getId() == null) {
      return 0;
    }
    return getId().compareTo(o.getId());
   }
    
}
