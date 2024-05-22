package com.ssafy.offistellink.file.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    void deleteFile(int id);
}
