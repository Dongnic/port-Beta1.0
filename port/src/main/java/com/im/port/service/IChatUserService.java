package com.im.port.service;

import com.im.port.vo.dto.ChatUserDto;
import com.im.port.vo.dto.UpdateChatUserDto;

public interface IChatUserService {
    Long postChatUser(ChatUserDto chatUserDto) throws Exception;
    int updateChatUser(UpdateChatUserDto UpdateChatUserDto)throws Exception; 
    int deleteChatUser(Long roomid, Long userid)throws Exception; 
}
