/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.controller;


import com.shristy.web.projectmanagement.entity.Timetable;
import com.shristy.web.projectmanagement.entity.TimetableForm;
import com.shristy.web.projectmanagement.entity.TimetableForm1;
import com.shristy.web.projectmanagement.entity.Timetablenext;
import com.shristy.web.projectmanagement.repository.TimetableRepository;
import com.shristy.web.projectmanagement.repository.TimetableRepository1;
import com.shristy.web.projectmanagement.repository.impl.TimetableServiceImpl;
import com.shristy.web.projectmanagement.repository.impl.TimetableServiceImpl1;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/admin/edit-newtable")
public class EditController1 {
    @Autowired
    private TimetableRepository1 ttr;

      @Autowired
    private TimetableServiceImpl1 ttrs;

    /**
     *
     * @param table
     * @return
     */
      @GetMapping
      public void fun(){
          
      }
    @PostMapping(value = "/save")
    public String edittable(@ModelAttribute TimetableForm1 timetable) throws SQLException, ClassNotFoundException{




//TimetableForm form=new TimetableForm(timetable.getListtimetable());

//              
//ttr.saveAll(form.getListtimetable());

////          System.out.println("rommno=-============="+table.getRoomno());
//
//

//              ttr.post("RoomNo(101)");
for(Timetablenext tab : timetable.getListtimetable()){
            System.out.println("==================>"+tab.getSeven15eight05());
//    tab.setRoomno("RoomNo(101)");
//  TimetableServiceImpl.updateStatic(tab);
Timetablenext time=ttr.getOne(tab.getId());
 time.setId(tab.getId());
time.setSeven15eight05(tab.getSeven15eight05());
           time.setEight05eight55(tab.getEight05eight55());
           time.setEight55nine45(tab.getEight55nine45());
           time.setNine45ten35(tab.getNine45ten35());
           time.setTen35eleven25(tab.getTen35eleven25());
           time.setEleven5twelve15(tab.getEleven5twelve15());
           time.setTwelve15one05(tab.getTwelve15one05());
           time.setOne5one50(tab.getOne5one50());
           time.setOne50two45(tab.getOne50two45());
           time.setTwo45three35(tab.getTwo45three35());
           time.setDays(tab.getDays());
          
           time.setRoomno(tab.getRoomno());
ttr.save(time);

//   ttr.postByRoomno(tab.getSeven15eight05(),
//           tab.getEight05eight55(),
//           tab.getEight55nine45(),
//           tab.getNine45ten35(),
//           tab.getTen35eleven25(),
//           tab.getEleven5twelve15(),
//           tab.getTwelve15one05(),
//           tab.getOne5one50(),
//           tab.getOne50two45(),
//           tab.getTwo45three35(),
//           tab.getDays(),tab.getId());
    }  
     return "redirect:/admin";
    }
   
}
