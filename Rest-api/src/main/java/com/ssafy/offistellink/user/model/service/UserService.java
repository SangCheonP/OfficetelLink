package com.ssafy.offistellink.user.model.service;

import com.ssafy.offistellink.user.model.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<UserDto> getAllUser() throws SQLException;
    boolean addUser(UserDto userDto);
    List<String> getAllEmail();
    UserDto login(UserDto userDto) throws Exception;
    void saveRefreshToken(String email, String refreshToken) throws Exception;
}
