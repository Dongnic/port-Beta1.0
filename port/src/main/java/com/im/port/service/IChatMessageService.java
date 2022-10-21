package com.im.port.service;

import java.util.List;

import com.im.port.vo.dto.ChatMessageDto;
import com.im.port.vo.dto.ChatRoomDto;
import com.im.port.vo.entity.ChatMessageEntity;

public interface IChatMessageService {
	List<ChatMessageDto> getMessagesByChatroomId(ChatRoomDto chatRoomDto) throws Exception;
	ChatMessageEntity postMessage(ChatMessageDto chatMessageDto) throws Exception;
	public void returnMessage(String destination, ChatMessageDto chatMessageDto);
	public void returnMessage(String destination, String message);
	public Long getMension(Long userid, Long chatroomid);
}