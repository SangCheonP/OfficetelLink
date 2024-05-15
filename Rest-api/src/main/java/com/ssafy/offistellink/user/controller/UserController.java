package com.ssafy.offistellink.user.controller;

import com.ssafy.offistellink.user.model.dto.UserDto;
import com.ssafy.offistellink.user.model.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User", description = "유저 정보 관리")
@RequestMapping("/user")
@CrossOrigin("*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 모든 유저 조회
    // GET /user
    @GetMapping("")
    public ResponseEntity<?> list() throws Exception{
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 
}
