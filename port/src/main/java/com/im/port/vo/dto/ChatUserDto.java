package com.im.port.vo.dto;

import com.im.port.vo.entity.ChatMessageEntity;
import com.im.port.vo.entity.ChatRoomEntity;
import com.im.port.vo.entity.ChatUserEntity;
import com.im.port.vo.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatUserDto {
    private Long id;
    private UserEntity userid;
    private ChatRoomEntity chatroomid;
    private ChatMessageEntity chatmessageid;

    public ChatUserEntity toEntity(){
        return ChatUserEntity.builder()
                .id(id)
                .userid(userid)
                .chatroomid(chatroomid)
                .chatmessageid(chatmessageid)
                .build();
    }
}
