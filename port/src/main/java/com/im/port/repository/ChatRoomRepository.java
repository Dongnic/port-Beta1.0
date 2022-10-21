package com.im.port.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.im.port.vo.entity.ChatRoomEntity;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    
    public Optional<ChatRoomEntity> findById(Long id);
    public List<ChatRoomEntity> findByUserid(Long id);

    @Query(value = " SELECT                         "
                 + "   a.*                          "
                 + " FROM chatroom a                "
                 + " LEFT JOIN chatuser b           "
                 + " ON a.chatroomid = b.chatroomid "
                 + " WHERE 1=1                      "
                 + " AND roomtype = 'CHATROOM'      "
                 + " AND b.userid = :userid ", nativeQuery = true)
    public List<ChatRoomEntity> selectSQLByUserid(@Param(value = "userid") Long id);

    @Query(value = " SELECT a.*                  "
                 + " FROM chatroom a             "
                 + " WHERE 1=1                   "
                 + " AND a.userid = :userid      "
                 + " AND a.otherid = :otherid    "
                 + " AND a.roomtype = 'DM'       "
                ,nativeQuery = true)
    public ChatRoomEntity selectDMSQLByUseridAndOtherid(@Param(value = "userid") Long userid, @Param(value = "otherid") Long otherid);

    @Transactional
    @Modifying
    @Query(value = " UPDATE chatroom SET            "
                 + " roomimage = :roomimage         "
                 + " WHERE 1=1                      "
                 + " AND chatroomid = :chatroomid   ", nativeQuery = true)
    public void updateSQLToRoomImage(@Param(value = "roomimage") String storedFileName, @Param(value = "chatroomid") Long id);

}
