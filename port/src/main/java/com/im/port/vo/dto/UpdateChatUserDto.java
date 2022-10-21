package com.im.port.vo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChatUserDto {
    private Long userid;
    private Long chatroomid;
    private Long chatmessageid;
}
