package com.ssafy.offistellink.file.controller;

import com.ssafy.offistellink.file.model.service.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@Tag(name = "FileDownload", description = "파일 다운로드")
@RequestMapping("/files")
@CrossOrigin("*")
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${file.path.upload-files}")
    private String uploadFilePath;
    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) throws IOException {
        File file = new File(uploadFilePath + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        // 파일 이름 인코딩
        String encodedFileName = encodeFileName(file.getName());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + encodedFileName + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(resource);
    }

    private String encodeFileName(String fileName) {
        return URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable int id) throws SQLException {
        fileService.deleteFile(id);
        return ResponseEntity.ok().build();
    }
}
