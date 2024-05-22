package com.ssafy.offistellink.user.model.service;

import com.ssafy.offistellink.user.model.dto.UserDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserDto> getAllUser() throws Exception;
    boolean addUser(UserDto userDto) throws Exception;
    List<String> getAllEmail() throws Exception;
    UserDto login(UserDto userDto) throws Exception;
    UserDto userInfo(String email) throws Exception;
    void saveRefreshToken(String email, String refreshToken) throws Exception;
    Object getRefreshToken(String email) throws Exception;
    void deleteRefreshToken(String email) throws Exception;
    void updateProfileImageUrl(String email, String imageUrl) throws Exception;
    String getProfileImageUrl(String email) throws Exception;
    boolean checkPassword(String email, String password) throws Exception;
    boolean updateProfile(Map<String, String> userInfo) throws Exception;
    boolean updateProfileBorderExp(Map<String, Object> updateInfo) throws Exception;
}
