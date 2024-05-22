package com.ssafy.offistellink.notice.model.service;

import com.ssafy.offistellink.file.model.dto.FileDto;
import com.ssafy.offistellink.notice.model.dto.NoticeDto;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface NoticeService {
    NoticeDto getNoticeById(int id) throws SQLException; //공지사항 id 조회

    List<NoticeDto> getAllNotices() throws SQLException; //공지사항 조회

    void insertNotice(NoticeDto noticeDto, List<MultipartFile> files) throws SQLException; //공지사항 등록

    void updateNotice(NoticeDto noticeDto, List<MultipartFile> files) throws SQLException, IOException; //공지사항 수정

    void deleteNotice(int id) throws SQLException, IOException; //공지사항 삭제

    void incrementView(int id) throws SQLException; //조회수 증가

    void incrementLikes(int id) throws SQLException; //좋아요 증가
}
