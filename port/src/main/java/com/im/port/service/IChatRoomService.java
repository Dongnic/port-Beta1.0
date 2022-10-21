package com.im.port.service;

import java.util.List;

import com.im.port.vo.dto.ChatRoomDto;
import com.im.port.vo.entity.ChatRoomEntity;

public interface IChatRoomService {
    ChatRoomEntity postRoom(ChatRoomDto chatRoomDto) throws Exception;
    ChatRoomEntity getDMRoom(Long userid, Long otherid) throws Exception;
    void deleteRoom(Long chatRoomId) throws Exception;
	List<ChatRoomDto> getAllChatRooms() throws Exception;
	List<ChatRoomDto> getChatRooms(Long id) throws Exception;
	ChatRoomDto getRoomInfo(Long id) throws Exception;
}
