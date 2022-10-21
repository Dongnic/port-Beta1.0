package com.im.port.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.im.port.config.security.auth.PrincipalDetails;
import com.im.port.service.UserServiceImpl;
import com.im.port.vo.dto.UserDto;
import com.im.port.vo.entity.UserEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

     //전체 조회
     @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<?> getUserList() throws Exception {
        log.info(" ##### UserController getUserList");
        List<UserDto> dtoList = userService.getUserList();
         return ResponseEntity.ok(dtoList);
     }
     //등록
    @PostMapping
    public ResponseEntity<String> postUser(@RequestBody UserDto userDto) throws Exception {
        log.info(" ##### UserController postUser");
        log.info(" ### userDto : " + userDto);
        // 패스워드 암호화
        String encPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encPassword);
        userDto.setRole("ROLE_MEMBER");
        Long result = userService.postUser(userDto);
        if(result < 0 ) return new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>("JOIN SUCCESS", HttpStatus.OK);
    }
    //수정 (Put은 들어온 값수정 후 나머지 default)
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchTodo(@PathVariable("id") Long id, @RequestBody UserDto userDto) throws Exception {
        log.info(" ##### UserController patchTodo");
        log.info(" ### userDto : " + userDto);
        log.info(" ### id : " + id);
        UserDto isChecked = userService.findUserById(id);
        if(isChecked == null){
            return new ResponseEntity<>("Fail", HttpStatus.NOT_FOUND);
        }
        userService.postUser(userDto);
        return new ResponseEntity<>("EDIT SUCCESS", HttpStatus.OK);
    }
    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) throws Exception {
        log.info(" ##### UserController deleteTodo");
        log.info(" ### id : " + id);
        userService.deleteUser(id);
        return new ResponseEntity<>("DELETE SUCCESS", HttpStatus.OK);
    }
    //단일 조회
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTodo(@PathVariable("id") Long id) throws Exception {
        log.info(" ##### UserController getTodo");
        log.info(" ### id : " + id);
        UserDto userDto = userService.findUserById(id);
        return ResponseEntity.ok(userDto);
    }
    // 로그인 회원정보
    @GetMapping("/userInfo")
    public UserEntity getUser(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails) {
        log.info(" ##### UserController getUser");
        log.info(" ### authentication.getPrincipal : " + authentication.getPrincipal());
        log.info(" ### userDetails.getUser : " + userDetails.getUser());
        return userDetails.getUser();
    }
    // 온라인 유저 리스트
    @GetMapping("/online/{chatroomid}")
    public ResponseEntity<List<UserDto>> getOnlineUserList(@PathVariable("chatroomid") Long chatroomid) throws Exception{
        log.info(" ##### UserController getOnlineUserList");
        List<UserDto> onlineUserList = userService.getOnlineUserList(chatroomid);
        return ResponseEntity.status(HttpStatus.OK).body(onlineUserList);
    }
    // 오프라인 유저 리스트
    @GetMapping("/offline/{chatroomid}")
    public ResponseEntity<List<UserDto>> getOfflineUserList(@PathVariable("chatroomid") Long chatroomid) throws Exception{
        log.info(" ##### UserController getOfflineUserList");
        List<UserDto> offlineUserList = userService.getOfflineUserList(chatroomid);
        return ResponseEntity.status(HttpStatus.OK).body(offlineUserList);
    }
    // 미참여 유저 리스트
    @GetMapping("/other/{chatroomid}")
    public ResponseEntity<List<UserDto>> getOtherUserList(@PathVariable("chatroomid") Long chatroomid) throws Exception{
        log.info(" ##### UserController getOtherUserList");
        List<UserDto> getOtherUserList = userService.getOtherUserList(chatroomid);
        return ResponseEntity.status(HttpStatus.OK).body(getOtherUserList);
    }
    // DM 유저 리스트
    @GetMapping("/DM/{userid}")
    public ResponseEntity<List<UserDto>> getDmUserList(@PathVariable("userid") Long userid) throws Exception{
        log.info(" ##### UserController getDmUserList");
        List<UserDto> getDmUserList = userService.getDmUserList(userid);
        return ResponseEntity.status(HttpStatus.OK).body(getDmUserList);
    }
}
