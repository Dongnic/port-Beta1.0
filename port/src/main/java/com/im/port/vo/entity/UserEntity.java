package com.im.port.vo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.im.port.config.security.oauth.provider.ProviderType;
import com.im.port.vo.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @DynamicInsert
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;
    
    // @Column(nullable = true)
    // @ColumnDefault("delete")
    private String email;

    // @Column(nullable = false)
    // @Column(nullable = true)
    // @ColumnDefault("delete")
    private String username;

    private String password;
    
    private String role; // ROLE_USER, ROLE_ADMIN
    
    // 구글 로그인
    @ColumnDefault("'N'")
    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private ProviderType provider; // "google"
    
    @ColumnDefault("'https://i.esdrop.com/d/f/14rMlVHaTh/lxwXM7NJnb.svg'")
    private String profileimage; // "sub"
    
    @CreationTimestamp
    private Timestamp regdate;

    public UserDto toDto(){
        return UserDto.builder()
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
