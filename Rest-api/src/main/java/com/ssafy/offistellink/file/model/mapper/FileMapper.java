package com.ssafy.offistellink.file.model.mapper;

import com.ssafy.offistellink.file.model.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface FileMapper {
    FileDto getFileById(int id);
    void insertFile(FileDto file);
    void deleteFilesByNoticeId(int noticeId);
}
