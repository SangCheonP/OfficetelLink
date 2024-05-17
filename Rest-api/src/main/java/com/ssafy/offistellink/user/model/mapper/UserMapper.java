package com.ssafy.offistellink.user.model.mapper;

import com.ssafy.offistellink.user.model.dto.UserDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<UserDto> getAllUser() throws SQLException;
    int addUser(UserDto userDto) throws SQLException;
    List<String> getAllEmail() throws SQLException;
    UserDto login(UserDto userDto) throws SQLException;
    UserDto userInfo(String email) throws SQLException;
    void saveRefreshToken(Map<String, String> map) throws SQLException;
}
