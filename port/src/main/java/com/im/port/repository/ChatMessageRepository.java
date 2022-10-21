package com.im.port.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.im.port.vo.entity.ChatMessageEntity;
import com.im.port.vo.entity.ChatRoomEntity;
 
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    public List<ChatMessageEntity> findByChatroomidOrderByRegdate(ChatRoomEntity chatRoomEntity); // Jpa query methods
    
    @Query(value =    " SELECT count(*)                     "
                    + " FROM chatmessage b                  "
                    + " WHERE 1=1                           "
                    + " AND roomtype = 'CHATROOM'           "
                    + " AND chatroomid = :chatroomid        "
                    + " AND chatmessageid > (               "
                    + "  SELECT a.chatmessageid             "
                    + "  FROM chatuser a                    "
                    + "  WHERE 1=1                          "
                    + "  AND userid = :userid               "
                    + "  AND chatroomid = :chatroomid )     ",
                    nativeQuery = true)
    public Long countMensionByChatroomidAndUserid(@Param(value = "userid") Long userid, @Param(value = "chatroomid") Long chatroomid);

}
