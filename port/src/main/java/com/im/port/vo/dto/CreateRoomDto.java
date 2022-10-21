package com.im.port.vo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomDto {
    private Long userid;
    private String title;
    private String discribe;
    private List<Long> inviteList;
}
