package com.ssafy.offistellink.notice.model.service;

import com.ssafy.offistellink.file.model.dto.FileDto;
import com.ssafy.offistellink.file.model.mapper.FileMapper;
import com.ssafy.offistellink.notice.model.dto.NoticeDto;
import com.ssafy.offistellink.notice.model.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

//    @Autowired
//    private FileMapper fileMapper;

    @Override
    public NoticeDto getNoticeById(int id) {
        noticeMapper.incrementView(id);
        return noticeMapper.getNoticeById(id);
    }

    @Override
    public List<NoticeDto> getAllNotices() {
        return noticeMapper.getAllNotices();
    }

    @Transactional
    public void insertNotice(NoticeDto noticeDto) throws SQLException {
//        NoticeDto noticeDto = new NoticeDto();
//        noticeDto.setTitle(title);
//        noticeDto.setContent(content);
//        noticeDto.setUserEmail(userEmail); // 사용자 이메일 설정

        noticeMapper.insertNotice(noticeDto);
    }

//    @Transactional
//    public void insertNotice(NoticeDto noticeDto, List<MultipartFile> files) {
//        noticeMapper.insertNotice(noticeDto);
//
//        if (files != null) {
//            for (MultipartFile file : files) {
//                try {
//                    // 파일 저장 로직 추가
//                    String filePath = "/path/to/save/" + file.getOriginalFilename();
//                    file.transferTo(new File(filePath));
//
//                    // 파일 정보 DB 저장 로직 추가
//                    FileDto fileDto = new FileDto();
//                    fileDto.setNoticeId(noticeDto.getId());
//                    fileDto.setOriginName(file.getOriginalFilename());
//                    fileDto.setPath(filePath);
//                    fileMapper.insertFile(fileDto);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    // 필요에 따라 예외 처리 추가
//                }
//            }
//        }
//    }

    @Transactional
    public void updateNotice(NoticeDto noticeDto) {
        if (noticeDto == null || noticeDto.getId() == 0) {
            throw new IllegalArgumentException("NoticeDto or Notice ID cannot be null");
        }

        // Notice 업데이트
        noticeMapper.updateNotice(noticeDto);

//        // 기존 파일 삭제
//        fileMapper.deleteFilesByNoticeId(noticeDto.getId());
//
//        // 새 파일 추가
//        if (noticeDto.getFiles() != null) {
//            for (FileDto file : noticeDto.getFiles()) {
//                if (file != null) {
//                    file.setNoticeId(noticeDto.getId());
//                    fileMapper.insertFile(file);
//                }
//            }
//        }
    }

    @Transactional
    public void deleteNotice(int id) {
        //fileMapper.deleteFilesByNoticeId(id);
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
}