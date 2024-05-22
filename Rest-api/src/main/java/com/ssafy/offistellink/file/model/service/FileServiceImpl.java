package com.ssafy.offistellink.file.model.service;

import com.ssafy.offistellink.file.model.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;


    @Transactional
    public void deleteFile(int id) {
        fileMapper.deleteFile(id);
    }
}
