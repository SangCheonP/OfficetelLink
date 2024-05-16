package com.ssafy.offistellink.user.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String phone;
    protected Integer point;
    private boolean admin;
    private Timestamp createdAt;
    private String refreshToken;
}
