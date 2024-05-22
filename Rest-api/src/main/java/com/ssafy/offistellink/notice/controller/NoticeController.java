package com.ssafy.offistellink.notice.controller;

import com.ssafy.offistellink.file.model.dto.FileDto;
import com.ssafy.offistellink.notice.model.dto.NoticeDto;
import com.ssafy.offistellink.notice.model.service.NoticeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Notice", description = "공지 사항 관리")
@RequestMapping("/notices")
@CrossOrigin("*")
@RestController
@Slf4j
public class NoticeController {
    @Value("${file.path}")
    private String uploadPath;

    @Value("${file.path.upload-images}")
    private String uploadImagePath;

    @Value("${file.path.upload-files}")
    private String uploadFilePath;

    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public List<NoticeDto> getAllNotices() throws SQLException {
        return noticeService.getAllNotices();
    }

    @PostMapping
    public ResponseEntity<NoticeDto> createNotice(@RequestPart("notice") NoticeDto noticeDto,
                                                  @RequestPart(value = "files", required = false) List<MultipartFile> files) throws SQLException, IOException {

        if (files != null) {
            noticeDto.setFiles(getFileList(noticeDto, files));
        }
        noticeService.insertNotice(noticeDto, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(noticeDto);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> incrementLikes(@PathVariable int id) throws SQLException {
        noticeService.incrementLikes(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public NoticeDto getNoticeNyId(@PathVariable int id) throws SQLException {
        return noticeService.getNoticeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoticeDto> updateNotice(@RequestPart("notice") NoticeDto noticeDto,
                                                  @RequestPart(value = "files", required = false) List<MultipartFile> files) throws SQLException, IOException {
        if (files != null) {
            noticeDto.setFiles(getFileList(noticeDto, files));
        } else {
            // 기존 파일 정보 유지
            NoticeDto existingNotice = noticeService.getNoticeById(noticeDto.getId());
            noticeDto.setFiles(existingNotice.getFiles());
        }
        noticeService.updateNotice(noticeDto, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(noticeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable int id) throws SQLException, IOException {
        noticeService.deleteNotice(id);
        return ResponseEntity.noContent().build();
    }

    private List<FileDto> getFileList(NoticeDto noticeDto, List<MultipartFile> files) throws IOException {
        List<FileDto> fileDtos = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String originName = file.getOriginalFilename();
                String savedName = System.currentTimeMillis() + "_" + originName;
                String filePath = Paths.get(uploadFilePath, savedName).toString();
                System.out.println(savedName + " " + filePath);

                FileDto fileDto = new FileDto();
                fileDto.setNoticeId(noticeDto.getId());
                fileDto.setOriginName(originName);
                fileDto.setSavedName(savedName);
                fileDto.setPath(filePath);

                file.transferTo(new File(filePath));
                fileDtos.add(fileDto);
                log.info("File added: {}", fileDto);
            }
        }
        return fileDtos;
    }

}
