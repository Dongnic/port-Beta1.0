package com.im.port.vo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.im.port.vo.dto.OnlineUserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "onlineuser")
public class OnlineUserEntity {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onlineuserid")
    private Long id;

    @Column(name = "userid", unique = true)
    private String userid;
    
    @Column(name = "sessionid")
    private String sessionid;

    public OnlineUserDto toDto(){
        return OnlineUserDto.builder()
                .id(id)
                .userid(userid)
                .sessionid(sessionid)
                .build();
    }
}
