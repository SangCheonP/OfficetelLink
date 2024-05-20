package com.ssafy.offistellink.notice.model.service;

import com.ssafy.offistellink.notice.model.dto.NoticeDto;

import java.sql.SQLException;
import java.util.List;

public interface NoticeService {
    NoticeDto getNoticeById(int id) throws SQLException; //공지사항 id 조회

    List<NoticeDto> getAllNotices() throws SQLException; //공지사항 조회

    void insertNotice(NoticeDto noticeDto) throws SQLException; //공지사항 등록

    void updateNotice(NoticeDto noticeDto) throws SQLException; //공지사항 수정

    void deleteNotice(int id) throws SQLException; //공지사항 삭제
}
