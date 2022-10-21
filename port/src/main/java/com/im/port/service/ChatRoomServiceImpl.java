package com.im.port.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.im.port.repository.ChatRoomRepository;
import com.im.port.vo.dto.ChatRoomDto;
import com.im.port.vo.entity.ChatRoomEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatRoomServiceImpl implements IChatRoomService {
	
	private final ChatRoomRepository repository;

	// 채팅방 등록 
	@Override
	public ChatRoomEntity postRoom(ChatRoomDto chatRoomDto) throws Exception {
		log.info(" ##### ChatRoomServiceImpl postRoom");
		return repository.save(chatRoomDto.toEntity());
	}
	// 전체 채팅방리스트 반환 
	@Override
	public List<ChatRoomDto> getAllChatRooms() throws Exception {
		log.info(" ##### ChatRoomServiceImpl getChatRooms");
		List<ChatRoomEntity> entityList = repository.findAll();
		List<ChatRoomDto> dtoList = new ArrayList<>();
        for (ChatRoomEntity entity : entityList) {
            dtoList.add(entity.toDto());
        }
        return dtoList;
	}
	// 참여 채팅방리스트 반환 
	@Override
	public List<ChatRoomDto> getChatRooms(Long id) throws Exception {
		log.info(" ##### ChatRoomServiceImpl getChatRooms");
		List<ChatRoomEntity> entityList = repository.selectSQLByUserid(id);
		List<ChatRoomDto> dtoList = new ArrayList<>();
        for (ChatRoomEntity entity : entityList) {
            dtoList.add(entity.toDto());
        }
        return dtoList;
	}
	// 특정 채팅방 정보 반환
	@Override
	public ChatRoomDto getRoomInfo(Long chatRoomId) throws Exception {
		log.info(" ##### ChatRoomServiceImpl getRoomInfo");
		return repository.findById(chatRoomId).orElseThrow().toDto();
	}
	// 특정 채팅방 삭제
	@Override
	public void deleteRoom(Long chatRoomId) throws Exception {
		log.info(" ##### ChatRoomServiceImpl deleteRoom");
		repository.delete(
            repository.findById(chatRoomId).orElseThrow());
	}
	@Override
	public ChatRoomEntity getDMRoom(Long userid, Long otherid) throws Exception {
		return repository.selectDMSQLByUseridAndOtherid(userid, otherid);
	}

}
