package com.ssafy.offistellink.file.model.service;

import com.ssafy.offistellink.file.model.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

public interface FileService {
    FileDto getFileById(int id) throws SQLException;

    void saveFile(MultipartFile file,FileDto fileDto) throws IOException;
}
