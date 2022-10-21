package com.im.port.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.im.port.repository.ChatMessageRepository;
import com.im.port.vo.dto.ChatMessageDto;
import com.im.port.vo.dto.ChatRoomDto;
import com.im.port.vo.entity.ChatMessageEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatMessageServiceImpl implements IChatMessageService {

    private final SimpMessagingTemplate template;
    private final ChatMessageRepository repository;

    @Override
    public List<ChatMessageDto> getMessagesByChatroomId(ChatRoomDto chatRoomDto) throws Exception {
        log.info(" ##### ChatMessageServiceImpl getMessagesByChatroomId");
        List<ChatMessageEntity> entityList = repository.findByChatroomidOrderByRegdate(chatRoomDto.toEntity());
        List<ChatMessageDto> dtoList = new ArrayList<>();
        for (ChatMessageEntity entity : entityList) {
            dtoList.add(entity.toDto());
        }
        return dtoList;
    }

    @Override
    public ChatMessageEntity postMessage(ChatMessageDto chatMessageDto) throws Exception {
        log.info(" ##### ChatMessageServiceImpl postMessage");
        return repository.save(chatMessageDto.toEntity());
    }

    @Override
    public void returnMessage(String destination, ChatMessageDto chatMessageDto) {
        template.convertAndSend(destination, chatMessageDto);
    }
    @Override
    public void returnMessage(String destination, String message) {
        template.convertAndSend(destination, message);
    }

    @Override
    public Long getMension(Long userid, Long chatroomid) {
        return repository.countMensionByChatroomidAndUserid(userid, chatroomid);
    }

    
}
