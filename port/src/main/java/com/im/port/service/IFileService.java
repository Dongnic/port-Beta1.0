package com.im.port.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.im.port.vo.dto.FileDto;

public interface IFileService {
    FileDto uploadRoomImage(List<MultipartFile> files, Long id);
}
