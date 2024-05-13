package com.ssafy.offistellink.user.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDto {
    private Integer id;
    private String account;
    private String name;
    private String email;
    private String password;
    private String phone;
    private boolean admin;
    private Timestamp created_at;
}
