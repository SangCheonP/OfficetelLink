package com.ssafy.offistellink.file.controller;

import com.ssafy.offistellink.file.model.dto.FileDto;
import com.ssafy.offistellink.file.model.service.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

@Tag(name = "File", description = "파일 관리")
@RequestMapping("/files")
@CrossOrigin("*")
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int id) throws FileNotFoundException, SQLException {
        FileDto fileDto = fileService.getFileById(id);

        if (fileDto == null || fileDto.getPath() == null) {
            throw new FileNotFoundException("File not found with id: " + id);
        }

        File file = new File(fileDto.getPath());
        if (!file.exists()) {
            throw new FileNotFoundException("File not found at path: " + fileDto.getPath());
        }

        Resource resource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOriginName() + "\"");
        headers.add(HttpHeaders.CONTENT_TYPE, fileDto.getType());

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
