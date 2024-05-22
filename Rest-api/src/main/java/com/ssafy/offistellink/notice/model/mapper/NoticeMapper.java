package com.ssafy.offistellink.notice.model.mapper;

import com.ssafy.offistellink.file.model.dto.FileDto;
import com.ssafy.offistellink.notice.model.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    NoticeDto getNoticeById(int id); //공지사항 id 조회

    List<NoticeDto> getAllNotices(); //공지사항 조회

    void insertNotice(NoticeDto noticeDto); //공지사항 등록

    void updateNotice(NoticeDto noticeDto); //공지사항 수정

    void deleteNotice(int id); //공지사항 삭제

    void incrementView(int id); //조회수 증가

    void incrementLikes(int id); //좋아요 증가

    //파일 관리
    void registerFile(FileDto fileDto);
    void deleteFile(int id);
    List<FileDto> fileList(int id);
}
