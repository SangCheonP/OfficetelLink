package com.ssafy.offistellink.notice.controller;

import com.ssafy.offistellink.notice.model.dto.NoticeDto;
import com.ssafy.offistellink.notice.model.service.NoticeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Tag(name = "Notice", description = "공지 사항 관리")
@RequestMapping("/notices")
@CrossOrigin("*")
@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public List<NoticeDto> getAllNotices() throws SQLException {
        return noticeService.getAllNotices();
    }

    @PostMapping
    public void createNotice(@RequestBody NoticeDto notice) throws SQLException {
        noticeService.insertNotice(notice);
    }

    @GetMapping("/{id}")
    public NoticeDto getNoticeNyId(@PathVariable int id) throws SQLException {
        return noticeService.getNoticeById(id);
    }

    @PutMapping("/{id}")
    public void updateNotice(@PathVariable int id, @RequestBody NoticeDto notice) throws SQLException {
        if (notice == null) {
            throw new IllegalArgumentException("Notice cannot be null");
        }
        notice.setId(id);
        noticeService.updateNotice(notice);
    }

    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable int id) throws SQLException {
        noticeService.deleteNotice(id);
    }
}
