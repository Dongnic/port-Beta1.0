package com.im.port.service;

import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.im.port.repository.ChatRoomRepository;
import com.im.port.repository.FileRepository;
import com.im.port.vo.dto.FileDto;
import com.im.port.vo.entity.FileEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileServiceImpl implements IFileService{

    private final static String imageDirectory = Paths.get("/home/pc41/Documents/finalproject/projects/real2/upload").toAbsolutePath() + "/images/";
    private final FileRepository fileRepository;
    private final ChatRoomRepository chatroomRepository;
    
    @Override
    public FileDto uploadRoomImage(List<MultipartFile> files, Long id) {
        System.out.println("==================== uploadRoomImage ========================");
        if(files == null) return null;
        MultipartFile multipartFile = files.get(0);
        String originalFileName = multipartFile.getOriginalFilename();
        String storedFileName = UUID.randomUUID() + getExtension(multipartFile);
        Long fileSize = multipartFile.getSize();
        String fancySize = fancySize(fileSize);
        FileEntity fileEntity = FileEntity.builder()
            .messageid(id)
            .originalFileName(originalFileName)
            .storedFileName(storedFileName)
            .fileSize(fileSize)
            .category("roomImage")
            .fancySize(fancySize)
            .build();
        fileRepository.save(fileEntity).toDto();
        chatroomRepository.updateSQLToRoomImage(storedFileName, id);
        try (FileOutputStream writer = new FileOutputStream(imageDirectory + storedFileName)) {
            writer.write(multipartFile.getBytes());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Fail to upload files.");
        }
        return fileEntity.toDto();
    }
    // 확장자 얻기
    private String getExtension(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        int index = fileName.indexOf(".");
        if (index > -1) {
            return fileName.substring(index);
        }
        return "";
    }
    // 파일 사이즈 표기변환
    private String fancySize(Long size) {
        DecimalFormat df = new DecimalFormat("#,###.0");
		if (size < 1024) { // 1k 미만
			return size + " Bytes";
		} else if (size < (1024 * 1024)) { // 1M 미만
			return df.format(size / 1024.0) + " KB";
		} else if (size < (1024 * 1024 * 1024)) { // 1G 미만
			return df.format(size / (1024.0 * 1024.0)) + " MB";
		} else {
			return df.format(size / (1024.0 * 1024.0 * 1024.0)) + " GB";
		}
	}
}
    
