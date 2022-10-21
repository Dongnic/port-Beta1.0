package com.im.port.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.im.port.vo.entity.ChatUserEntity;

public interface ChatUserRepository extends JpaRepository<ChatUserEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = " UPDATE chatuser SET            "
                 + " chatmessageid = :chatmessageid "
                 + " WHERE 1=1                      "
                 + " AND userid = :userid           "
                 + " AND chatroomid = :chatroomid   "
                 , nativeQuery = true)
    public int updateSQLToChatmessageid(@Param("chatmessageid") Long chatmessageid
                                       , @Param("userid") Long userid
                                       , @Param("chatroomid") Long chatroomid);
    
    @Transactional
    @Modifying
    @Query(value = " DELETE FROM chatuser           "
                 + " WHERE 1=1                      "
                 + " AND chatroomid = :chatroomid   "
                 + " AND userid = :userid           "
                 , nativeQuery = true)                               
    public int deleteSQLByRoomidAndUserid(@Param("chatroomid") Long roomid, @Param("userid") Long userid);

}
