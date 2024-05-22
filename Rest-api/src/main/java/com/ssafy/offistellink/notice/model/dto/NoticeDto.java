package com.ssafy.offistellink.notice.model.dto;

import com.ssafy.offistellink.file.model.dto.FileDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NoticeDto {
    private int id;
    private String title;
    private String content;
    private int views;
    private LocalDateTime createdAt; //작성일자
    private LocalDateTime updatedAt; //갱신일자
    private boolean isPinned; //고정 여부
    private int isLike; //좋아요

    private String userEmail;
    private List<FileDto> files; //업로드할 파일
}
