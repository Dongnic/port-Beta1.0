package com.im.port.vo.dto;

import java.sql.Timestamp;

import com.im.port.config.security.oauth.provider.ProviderType;
import com.im.port.vo.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role; // ROLE_USER, ROLE_ADMIN
    // 구글 로그인
    private ProviderType provider; // "google"
    private String profileimage; // "sub"
    private Timestamp regdate;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(id)
                .username(username)
                .email(email)
                .password(password)
                .role(role)
                .provider(provider)
                .profileimage(profileimage)
                .regdate(regdate)
                .build();
    }
}
