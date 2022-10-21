package com.im.port.vo.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.im.port.vo.dto.ChatMessageDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chatmessage")
public class ChatMessageEntity {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatmessageid")
    private Long id;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="userid")
    private UserEntity userid;

    @ManyToOne(targetEntity = ChatRoomEntity.class, fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    @JoinColumn(name="chatroomid")
    private ChatRoomEntity chatroomid;
    
    @Column(columnDefinition = "LONGTEXT")
    private String message;
    
    @ColumnDefault("'CHATROOM'")
    private String roomtype; // "sub"

    @CreationTimestamp
    private Timestamp regdate;

    public ChatMessageDto toDto(){
        return ChatMessageDto.builder()
                .id(id)
                .userid(userid)
                .chatroomid(chatroomid)
                .message(message)
                .roomtype(roomtype)
                .regdate(regdate)
                .build();
    }    
}
