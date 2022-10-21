package com.im.port.vo.dto;

import com.im.port.vo.entity.OnlineUserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OnlineUserDto {

    private Long id;
    private String userid;
    private String sessionid;

    public OnlineUserEntity toEntity(){
        return OnlineUserEntity.builder()
                .id(id)
                .userid(userid)
                .sessionid(sessionid)
                .build();
    }
}
