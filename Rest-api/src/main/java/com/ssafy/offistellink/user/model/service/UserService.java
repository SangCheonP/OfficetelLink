package com.ssafy.offistellink.user.model.service;

import com.ssafy.offistellink.user.model.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers() throws SQLException;
}
