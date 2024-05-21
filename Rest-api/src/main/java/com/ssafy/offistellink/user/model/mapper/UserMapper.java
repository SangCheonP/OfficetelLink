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
    Object getRefreshToken(String email) throws SQLException;
    void deleteRefreshToken(Map<String, String> map) throws SQLException;
    void updateProfileImageUrl(Map<String, String> map) throws SQLException;
    String getProfileImageUrl(String email) throws SQLException;
    String checkPassword(Map<String, String> map) throws SQLException;
    int updateProfile(Map<String, String> userInfo) throws SQLException;
}
