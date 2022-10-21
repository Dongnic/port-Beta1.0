package com.im.port.service;

import java.util.List;

import com.im.port.vo.dto.UserDto;

public interface IUserService {
    
    List<UserDto> getUserList() throws Exception;
    Long postUser(UserDto userDto) throws Exception;
    void deleteUser(Long id) throws Exception;
    UserDto findUserById(Long id) throws Exception;
    List<UserDto> getOnlineUserList(Long chatroomid) throws Exception;
    List<UserDto> getOfflineUserList(Long chatroomid) throws Exception;
    List<UserDto> getOtherUserList(Long chatroomid) throws Exception;
    List<UserDto> getDmUserList(Long userid) throws Exception;
}
