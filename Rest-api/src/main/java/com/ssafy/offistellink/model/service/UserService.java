package com.ssafy.offistellink.model.service;

import com.ssafy.offistellink.model.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers() throws SQLException;
}
