package com.im.port.service;

import org.springframework.stereotype.Service;

import com.im.port.repository.ChatUserRepository;
import com.im.port.vo.dto.ChatUserDto;
import com.im.port.vo.dto.UpdateChatUserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatUserServiceImpl implements IChatUserService{

    private final ChatUserRepository repository;

    @Override
    public Long postChatUser(ChatUserDto chatUserDto) throws Exception {
        log.info(" ##### ChatUserServiceImpl postChatUser");
        return repository.save(chatUserDto.toEntity()).getId();
    }

    @Override
    public int updateChatUser(UpdateChatUserDto chatUserDto) throws Exception {
        return repository.updateSQLToChatmessageid(chatUserDto.getChatmessageid(), chatUserDto.getUserid(), chatUserDto.getChatroomid());
    }

    @Override
    public int deleteChatUser(Long roomid, Long userid) throws Exception {
        return repository.deleteSQLByRoomidAndUserid(roomid, userid);
    }
    
}
