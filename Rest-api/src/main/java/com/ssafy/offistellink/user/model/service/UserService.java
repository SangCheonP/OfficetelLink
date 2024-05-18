package com.ssafy.offistellink.user.model.service;

import com.ssafy.offistellink.user.model.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<UserDto> getAllUser() throws Exception;
    boolean addUser(UserDto userDto) throws Exception;
    List<String> getAllEmail() throws Exception;
    UserDto login(UserDto userDto) throws Exception;
    UserDto userInfo(String email) throws Exception;
    void saveRefreshToken(String email, String refreshToken) throws Exception;
    Object getRefreshToken(String email) throws Exception;
    void deleteRefreshToken(String email) throws Exception;
}
