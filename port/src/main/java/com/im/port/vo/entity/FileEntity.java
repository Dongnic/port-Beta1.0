package com.im.port.vo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.im.port.vo.dto.FileDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "file")
public class FileEntity {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileid")
    private Long id;

    @Column(nullable = false)
    private Long messageid;
    @Column(nullable = false)
    private String originalFileName;
    @Column(nullable = false)
    private String storedFileName;

    private Long fileSize;
    private String fancySize;
    private String category;

    public FileDto toDto(){
        return FileDto.builder()
                .id(id)
                .messageid(messageid)
                .originalFileName(originalFileName)
                .storedFileName(storedFileName)
                .fileSize(fileSize)
                .category(category)
                .build();
    }
}
    
