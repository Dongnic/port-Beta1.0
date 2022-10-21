package com.im.port.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.im.port.vo.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    
}
