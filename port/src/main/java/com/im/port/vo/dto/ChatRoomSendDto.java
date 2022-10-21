package com.im.port.vo.dto;

import java.sql.Timestamp;

import com.im.port.vo.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomSendDto {
    private Long id;
    private String title;
    private String discribe;
    private String roomimage;
    private UserEntity userid;
    private Timestamp regdate;
    private Long mentions;
}
