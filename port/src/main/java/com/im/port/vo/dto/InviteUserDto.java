package com.im.port.vo.dto;

import java.util.List;

import com.im.port.vo.entity.ChatRoomEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InviteUserDto {
    private ChatRoomEntity chatroomid;
    private List<Long> inviteList;
}
