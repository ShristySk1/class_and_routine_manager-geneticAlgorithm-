/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shristy.web.projectmanagement.repository;


import com.shristy.web.projectmanagement.entity.Room;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**ssss
 *
 * @author Administrator
 */
@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{
   
    @Query(value="select r.room_id from room r",nativeQuery=true)
  public List<Long> findRespectiveIds();
    @Query(value="select * from room t where t.room_value=?1",nativeQuery=true)
    public Room findByRoom(String room);
//  @Query(value="select r.room_id from room r where room_id in :ids",nativeQuery=true)
  public List<Room> findByRoomIdIn(List<Long> ids);
}
