package com.ssafy.offistellink.notice.model.service;

import com.ssafy.offistellink.file.model.dto.FileDto;
import com.ssafy.offistellink.file.model.mapper.FileMapper;
import com.ssafy.offistellink.notice.model.dto.NoticeDto;
import com.ssafy.offistellink.notice.model.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public NoticeDto getNoticeById(int id) {
        return noticeMapper.getNoticeById(id);
    }

    @Override
    public List<NoticeDto> getAllNotices() {
        return noticeMapper.getAllNotices();
    }

    @Transactional
    public void insertNotice(NoticeDto noticeDto) {
        noticeMapper.insertNotice(noticeDto);

        if (noticeDto.getFiles() != null) {
            for (FileDto file : noticeDto.getFiles()) {
                file.setNoticeId(noticeDto.getId());
                fileMapper.insertFile(file);
            }
        }
    }

    @Transactional
    public void updateNotice(NoticeDto noticeDto) {

        if (noticeDto == null || noticeDto.getId() == 0) {
            throw new IllegalArgumentException("NoticeDto or Notice ID cannot be null");
        }

        // Notice 업데이트
        noticeMapper.updateNotice(noticeDto);

        // 기존 파일 삭제
        fileMapper.deleteFilesByNoticeId(noticeDto.getId());

        // 새 파일 추가
        if (noticeDto.getFiles() != null) {
            for (FileDto file : noticeDto.getFiles()) {
                if (file != null) {
                    file.setNoticeId(noticeDto.getId());
                    fileMapper.insertFile(file);
                }
            }
        }
    }

    @Transactional
    public void deleteNotice(int id) {
        fileMapper.deleteFilesByNoticeId(id);
        noticeMapper.deleteNotice(id);
    }
}
