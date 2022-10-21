package com.im.port.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.im.port.repository.ChatRoomRepository;
import com.im.port.repository.FileRepository;
import com.im.port.vo.dto.FileDto;
import com.im.port.vo.entity.FileEntity;

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = "*")
@RestController
public class FileController {

    private final static String imageDirectory = Paths.get("/home/pc41/Documents/finalproject/projects/real2/upload").toAbsolutePath() + "/images/";
    private final FileRepository fileRepository;
    private final ChatRoomRepository chatroomRepository;
    @Autowired
    public FileController(FileRepository fileRepository, ChatRoomRepository chatroomRepository) {
        this.fileRepository = fileRepository;
        this.chatroomRepository = chatroomRepository;
        File file = new File(imageDirectory);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private String getExtension(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        int index = fileName.indexOf(".");
        if (index > -1) {
            return fileName.substring(index);
        }
        return "";
    }

    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> image(@PathVariable String fileName) throws FileNotFoundException {
        String filePath = imageDirectory + fileName;
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(filePath));
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStreamResource);
    }

    @GetMapping("/files")
    public List<String> getFileNames() {
        return Stream.of(new File(imageDirectory).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }
    // 파일 업로드
    @PostMapping(value = "/files")
    public ResponseEntity<List<FileDto>> uploadFiles(@RequestParam("files") List<MultipartFile> files, @RequestParam("messageid") Long messageid) {
        System.out.println("==================== uploadFiles ========================");
        if(files == null) return null;
        List<FileDto> fileList = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String originalFileName = multipartFile.getOriginalFilename();
            String storedFileName = UUID.randomUUID() + getExtension(multipartFile);
            Long fileSize = multipartFile.getSize();
            String fancySize = fancySize(fileSize);
            FileEntity fileEntity = FileEntity.builder()
                .messageid(messageid)
                .originalFileName(originalFileName)
                .storedFileName(storedFileName)
                .fileSize(fileSize)
                .category("file")
                .fancySize(fancySize)
                .build();
                fileList.add(fileRepository.save(fileEntity).toDto());
            try (FileOutputStream writer = new FileOutputStream(imageDirectory+storedFileName)) {
                writer.write(multipartFile.getBytes());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException("Fail to upload files.");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(fileList);
    }
    // 방 사진 업로드
    @PostMapping(value = "/roomImage")
    public ResponseEntity<FileDto> uploadRoomImage(@RequestParam("files") List<MultipartFile> files, @RequestParam("chatroomid") Long chatroomid) {
        System.out.println("==================== uploadRoomImage ========================");
        if(files == null) return null;
        MultipartFile multipartFile = files.get(0);
        String originalFileName = multipartFile.getOriginalFilename();
        String storedFileName = UUID.randomUUID() + getExtension(multipartFile);
        Long fileSize = multipartFile.getSize();
        String fancySize = fancySize(fileSize);
        FileEntity fileEntity = FileEntity.builder()
            .messageid(chatroomid)
            .originalFileName(originalFileName)
            .storedFileName(storedFileName)
            .fileSize(fileSize)
            .category("roomImage")
            .fancySize(fancySize)
            .build();
        fileRepository.save(fileEntity).toDto();
        chatroomRepository.updateSQLToRoomImage(storedFileName, chatroomid);
        try (FileOutputStream writer = new FileOutputStream(imageDirectory + storedFileName)) {
            writer.write(multipartFile.getBytes());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Fail to upload files.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(fileEntity.toDto());
    }

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