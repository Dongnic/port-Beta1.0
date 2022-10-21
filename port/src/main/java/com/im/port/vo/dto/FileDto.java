package com.im.port.vo.dto;

import com.im.port.vo.entity.FileEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDto {
    private Long id;
    private Long messageid;
    private String originalFileName;
    private String storedFileName;
    private String category;
    private Long fileSize;

    public FileEntity toDto(){
        return FileEntity.builder()
                .id(id)
                .messageid(messageid)
                .originalFileName(originalFileName)
                .storedFileName(storedFileName)
                .category(category)
                .fileSize(fileSize)
                .build();
    }
}
