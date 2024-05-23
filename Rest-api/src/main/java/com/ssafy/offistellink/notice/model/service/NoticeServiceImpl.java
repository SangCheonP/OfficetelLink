package com.ssafy.offistellink.notice.model.service;

import com.ssafy.offistellink.file.model.dto.FileDto;
import com.ssafy.offistellink.notice.model.dto.NoticeDto;
import com.ssafy.offistellink.notice.model.mapper.NoticeMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    @Value("${file.path}")
    private String uploadPath;

    @Value("${file.path.upload-files}")
    private String uploadFilePath;

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public NoticeDto getNoticeById(int id) {
        noticeMapper.incrementView(id);
        return noticeMapper.getNoticeById(id);
    }

    @Override
    public List<NoticeDto> getAllNotices() {
        return noticeMapper.getAllNotices();
    }

    @SneakyThrows
    @Transactional
    public void insertNotice(NoticeDto noticeDto, List<MultipartFile> files) throws SQLException {
        noticeMapper.insertNotice(noticeDto);
        saveFiles(noticeDto);
    }

    @Transactional
    public void updateNotice(NoticeDto noticeDto, List<MultipartFile> files) throws SQLException, IOException {
        noticeMapper.updateNotice(noticeDto);
        saveFiles(noticeDto);
    }

    @Transactional
    public void deleteNotice(int id) throws IOException {
        List<FileDto> files = noticeMapper.fileList(id);
        for (FileDto file : files) {
            Path filePath = Paths.get(uploadFilePath, file.getSavedName()).normalize();
            Files.delete(filePath);
        }
        noticeMapper.deleteFile(id);
        noticeMapper.deleteNotice(id);
    }

    @Override
    public void incrementView(int id) {
        noticeMapper.incrementView(id);
    }

    @Transactional
    public void incrementLikes(int id) {
        noticeMapper.incrementLikes(id);
    }

    @Override
    public List<Map<String, Object>> getRecentNoticesByEmail(String email) throws Exception {
        return noticeMapper.getRecentNoticesByEmail(email);
    }

    private void saveFiles(NoticeDto noticeDto) {
        List<FileDto> files = noticeDto.getFiles();
        if (files != null && !files.isEmpty()) {
            for (FileDto file : files) {
                try {
                    file.setNoticeId(noticeDto.getId());
                    log.info("Registering file: {}", file);
                    noticeMapper.registerFile(file);
                } catch (Exception e) {
                    log.error("Error registering file: {}", file, e);
                }
            }
        }
    }
}