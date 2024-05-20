package com.ssafy.offistellink.file.model.service;

import com.ssafy.offistellink.file.model.dto.FileDto;
import com.ssafy.offistellink.file.model.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;
    public FileDto getFileById(int id) {
        return fileMapper.getFileById(id);
    }

    @Override
    public void saveFile(MultipartFile file, FileDto fileDto) throws IOException {
        String uploadDir = "C:/Temp/uploads/images/";
        createDirectoryIfNotExists(uploadDir);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File uploadFile = new File(uploadDir + fileName);

        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            throw new IOException("Failed to transfer file to: " + uploadFile.getAbsolutePath(), e);
        }

        // 이미지 파일의 경로를 파일 DTO에 설정
        fileDto.setOriginName(file.getOriginalFilename());
        fileDto.setSavedName(fileName);
        fileDto.setPath(uploadFile.getAbsolutePath());

        // MIME 타입 확인
        Path path = Paths.get(uploadFile.getAbsolutePath());
        String mimeType = Files.probeContentType(path);
        if (mimeType == null) {
            mimeType = file.getContentType();
        }
        fileDto.setType(mimeType != null ? mimeType : "application/octet-stream");

        System.out.println(uploadFile.getAbsolutePath());
    }

    private void createDirectoryIfNotExists(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Directories created: " + directoryPath);
            } catch (IOException e) {
                throw new IOException("Failed to create directories: " + directoryPath, e);
            }
        }
    }
}