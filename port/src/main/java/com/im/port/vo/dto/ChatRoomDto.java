package com.im.port.vo.dto;

import java.sql.Timestamp;

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
public class ChatRoomDto {
    private Long id;
    private String title;
    private String discribe;
    private String roomimage; 
    private UserEntity userid;
    private Timestamp regdate;
    private String roomtype; // "sub"
    private Long otherid; // "sub
    
    public ChatRoomEntity toEntity(){
        return ChatRoomEntity.builder()
                .id(id)
                .title(title)
                .discribe(discribe)
                .roomimage(roomimage)
                .userid(userid)
                .otherid(otherid)
                .roomtype(roomtype)
                .regdate(regdate)
                .build();
    }
}
