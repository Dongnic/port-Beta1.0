package com.im.port.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.im.port.repository.ChatRoomRepository;
import com.im.port.service.IChatMessageService;
import com.im.port.service.IChatRoomService;
import com.im.port.service.IChatUserService;
import com.im.port.service.IUserService;
import com.im.port.vo.dto.ChatMessageDto;
import com.im.port.vo.dto.ChatRoomDto;
import com.im.port.vo.dto.ChatRoomSendDto;
import com.im.port.vo.dto.ChatUserDto;
import com.im.port.vo.dto.CreateDMDto;
import com.im.port.vo.dto.CreateRoomDto;
import com.im.port.vo.dto.InviteUserDto;
import com.im.port.vo.dto.UpdateChatUserDto;
import com.im.port.vo.dto.UserDto;
import com.im.port.vo.entity.ChatRoomEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@RestController
public class ChatRoomController {

	private final IChatRoomService chatRoomService;
	private final IUserService userService;
	private final IChatUserService chatUserService;
	private final IChatMessageService chatMessageService;
	private final ChatRoomRepository chatRoomRepository;


	// 채팅방 생성
	@PostMapping("/room")
	public ResponseEntity<?> postRoom(@RequestBody CreateRoomDto createRoomDto) throws Exception {
		log.info(" ##### ChatRoomController postRoom");
		System.out.println("CreateRoomDto getInviteList : "+createRoomDto.getInviteList());
		UserDto userDto = userService.findUserById(createRoomDto.getUserid());
		// 방 만들고 방 번호 반환
		ChatRoomDto chatRoomDto= new ChatRoomDto();
		chatRoomDto.setUserid(userDto.toEntity());
		chatRoomDto.setTitle(createRoomDto.getTitle());
		chatRoomDto.setOtherid(Long.parseLong("0"));
		chatRoomDto.setDiscribe(createRoomDto.getDiscribe());
		chatRoomDto.setRoomtype("CHATROOM");
		ChatMessageDto chatMessageDto= new ChatMessageDto();
		chatMessageDto.setId(Long.parseLong("1"));
		ChatRoomEntity creatRoomResult = chatRoomService.postRoom(chatRoomDto);
		log.info(" ### ChatRoomController postRoom SUCCESS");
		if (creatRoomResult.getId() >= 0){
			ChatUserDto chatUserDto= new ChatUserDto();
			chatUserDto.setChatroomid(creatRoomResult);
			chatUserDto.setUserid(chatRoomDto.getUserid());
			chatUserDto.setChatmessageid(chatMessageDto.toEntity());
			Long postChatUserResult = chatUserService.postChatUser(chatUserDto);
			log.info(" ### chatUserDto : " + chatUserDto);
			log.info(" #### ChatRoomController postChatUser");
			log.info(" ### postChatUserResult : " + postChatUserResult);
			for(int i=0; i<createRoomDto.getInviteList().size();i++){
				userDto = userService.findUserById(createRoomDto.getInviteList().get(i));
				chatUserDto.setUserid(userDto.toEntity());
				postChatUserResult = chatUserService.postChatUser(chatUserDto);
			}
			return ResponseEntity.status(HttpStatus.OK).body(creatRoomResult.getId());
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CREATE FAIL");
	}
	// 1:1채팅 생성
	@PostMapping("/DM")
	public ResponseEntity<?> postDMRoom(@RequestBody CreateDMDto craterDMDto) throws Exception {
		log.info(" ##### ChatRoomController postDMRoom");
		Long userid = craterDMDto.getUserid();
		Long otherid = craterDMDto.getOtherid();
<<<<<<< HEAD
		ChatRoomEntity chatRoomEntity1 = chatRoomRepository.selectDMSQLByUseridAndOtherid(userid,otherid);
		ChatRoomEntity chatRoomEntity2 = chatRoomRepository.selectDMSQLByUseridAndOtherid(otherid,userid);
		if(chatRoomEntity1 != null) return ResponseEntity.status(HttpStatus.OK).body(chatRoomEntity1.toDto());
		else if(chatRoomEntity2 != null) return ResponseEntity.status(HttpStatus.OK).body(chatRoomEntity2.toDto());
		
=======
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
		UserDto userDto = userService.findUserById(userid);
		UserDto otherDto = userService.findUserById(otherid);
		// 방 만들고 방 번호 반환
		ChatRoomEntity chatRoomEntity = chatRoomService.getDMRoom(userid, otherid);
		if(chatRoomEntity != null){
			return ResponseEntity.status(HttpStatus.OK).body(chatRoomEntity.toDto());
		}
		ChatRoomDto chatRoomDto= new ChatRoomDto();
		chatRoomDto.setUserid(userDto.toEntity());
		chatRoomDto.setOtherid(otherid);
		chatRoomDto.setRoomtype("DM");
		chatRoomDto.setTitle(userDto.getUsername()+" & "+otherDto.getUsername());
		chatRoomDto.setDiscribe(userDto.getUsername()+" & "+otherDto.getUsername()+ " 오붓한 시간");
		ChatMessageDto chatMessageDto= new ChatMessageDto();
		chatMessageDto.setId(Long.parseLong("1"));
		ChatRoomEntity creatRoomResult = chatRoomService.postRoom(chatRoomDto);
		log.info(" ### ChatRoomController postRoom SUCCESS");
		if (creatRoomResult.getId() >= 0){
			ChatUserDto chatUserDto= new ChatUserDto();
			chatUserDto.setChatroomid(creatRoomResult);
			chatUserDto.setChatmessageid(chatMessageDto.toEntity());
			chatUserDto.setUserid(userDto.toEntity());
			Long resultUser = chatUserService.postChatUser(chatUserDto);
			chatUserDto.setUserid(otherDto.toEntity());
			Long resultOther = chatUserService.postChatUser(chatUserDto);
			System.out.println(resultUser+resultOther);
<<<<<<< HEAD
			return ResponseEntity.status(HttpStatus.OK).body(creatRoomResult.toDto());
=======
			return ResponseEntity.status(HttpStatus.OK).body(creatRoomResult.getId());
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CREATE FAIL");
	}
	// 전체 채팅방 목록 반환
	@GetMapping("/rooms")
	public ResponseEntity<List<ChatRoomDto>> getAllRoomList() throws Exception {
		log.info(" ##### ChatRoomController getAllRoomList");
		List<ChatRoomDto> roomList = chatRoomService.getAllChatRooms();
		if (roomList == null || roomList.size() == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		return ResponseEntity.status(HttpStatus.OK).body(roomList);
	}
	// 참여 채팅방 목록 반환
	@GetMapping("/rooms/{id}")
	public ResponseEntity<List<ChatRoomSendDto>> getRoomList(@PathVariable("id") Long userid) throws Exception {
		log.info(" ##### ChatRoomController getRoomList");
		log.info(" ### id : " + userid);
		List<ChatRoomDto> roomList = chatRoomService.getChatRooms(userid);
		List<ChatRoomSendDto> chatRoomSendDtoList = new ArrayList<>();
		for(int i=0; i < roomList.size(); i++){
			Long cnt = chatMessageService.getMension(userid, roomList.get(i).getId());
			ChatRoomSendDto chatRoomSendDto = new ChatRoomSendDto();
			chatRoomSendDto.setId(roomList.get(i).getId());
			chatRoomSendDto.setTitle(roomList.get(i).getTitle());
			chatRoomSendDto.setDiscribe(roomList.get(i).getDiscribe());
			chatRoomSendDto.setRoomimage(roomList.get(i).getRoomimage());
			chatRoomSendDto.setUserid(roomList.get(i).getUserid());
			chatRoomSendDto.setMentions(cnt);
			chatRoomSendDto.setRegdate(roomList.get(i).getRegdate());
			chatRoomSendDtoList.add(chatRoomSendDto);
		}
		if (chatRoomSendDtoList == null || chatRoomSendDtoList.size() == 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		return ResponseEntity.status(HttpStatus.OK).body(chatRoomSendDtoList);
	}

	// 특정 채팅방 정보 반환
	@GetMapping("/room/{id}")
	public ResponseEntity<ChatRoomDto> getRoomInfo(@PathVariable("id") Long id) throws Exception {
		log.info(" ##### ChatRoomController roomInfo");
		log.info(" ### id : " + id);
		ChatRoomDto chatRoomDto = chatRoomService.getRoomInfo(id);
		if (chatRoomDto == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		return ResponseEntity.status(HttpStatus.OK).body(chatRoomDto);
	}
	
	// 특정 채팅방 메세지 반환
	@GetMapping("/room/message/{id}")
	public ResponseEntity<List<ChatMessageDto>> getMessageList(@PathVariable("id") Long id) throws Exception {
		log.info(" ##### ChatRoomController getMessageList");
		ChatRoomDto chatRoomDto = new ChatRoomDto();
		chatRoomDto.setId(id);
		List<ChatMessageDto> msgList = chatMessageService.getMessagesByChatroomId(chatRoomDto);
		return ResponseEntity.status(HttpStatus.OK).body(msgList);
	}
	// 마지막 메세지 
	@PutMapping("/chatuser")
	public ResponseEntity<?> updateChatMessage(@RequestBody UpdateChatUserDto chatUserDto) throws Exception{
		log.info(" ##### ChatRoomController updateChatMessage");
		int result = chatUserService.updateChatUser(chatUserDto);
		if (result > 0)
		return ResponseEntity.status(HttpStatus.OK).body(result);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("UPDATE FAIL");
	}
	
	// 특정 채팅방 나가기
	@DeleteMapping("/room/{roomid}/{userid}")
	public ResponseEntity<?> deleteChatUser(@PathVariable("roomid") Long roomid, @PathVariable("userid") Long userid) throws Exception{
		log.info(" ##### ChatRoomController deleteChatUser");
		int result = chatUserService.deleteChatUser(roomid, userid);
		if (result > 0)
		return ResponseEntity.status(HttpStatus.OK).body("퇴장 성공");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DELETE FAIL");
	}
	// 유저 초대
	@PostMapping("/room/invite")
	public ResponseEntity<?> insertChatUser(@RequestBody InviteUserDto inviteUserDto) throws Exception{ 
		log.info(" ##### ChatRoomController insertChatUser");
		ChatUserDto chatUserDto= new ChatUserDto();
		chatUserDto.setChatroomid(inviteUserDto.getChatroomid());
		Long result=null;
		for(int i=0; i<inviteUserDto.getInviteList().size();i++){
			UserDto userDto = userService.findUserById(inviteUserDto.getInviteList().get(i));
			chatUserDto.setUserid(userDto.toEntity());
			result = chatUserService.postChatUser(chatUserDto);
		}
		if (result != null && result > 0)
		return ResponseEntity.status(HttpStatus.OK).body("초대 성공");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVITE FAIL");
	}
}