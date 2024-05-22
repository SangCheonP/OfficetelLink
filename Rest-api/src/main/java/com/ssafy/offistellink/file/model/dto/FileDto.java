package com.ssafy.offistellink.file.model.dto;

import lombok.Data;

@Data
public class FileDto {
    private int id;

    private String originName;
    private String savedName;
    private String path;

    private int noticeId;
}
