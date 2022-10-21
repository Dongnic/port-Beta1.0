package com.im.port.vo.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.im.port.vo.entity.ChatMessageEntity;
import com.im.port.vo.entity.ChatRoomEntity;
import com.im.port.vo.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDto {
    
    private Long id;

    // JSON 받아올 때 객체타입으로 받아와야함
    private UserEntity userid;
    private ChatRoomEntity chatroomid;
    private String message;
    private String roomtype; // "sub"

    @JsonFormat(pattern = "HH:mm MM월dd일")
    private Timestamp regdate;

    public ChatMessageEntity toEntity(){
        return ChatMessageEntity.builder()
                .id(id)
                .userid(userid)
                .chatroomid(chatroomid)
                .message(message)
                .roomtype(roomtype)
                .regdate(regdate)
                .build();
    }    
}
