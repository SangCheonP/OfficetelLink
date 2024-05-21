package com.ssafy.offistellink.user.model.service;

import com.ssafy.offistellink.user.model.dto.UserDto;
import com.ssafy.offistellink.user.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> getAllUser() throws Exception{
        return userMapper.getAllUser();
    }

    @Override
    public boolean addUser(UserDto userDto) throws Exception{
        int result = userMapper.addUser(userDto);
        if(result == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAllEmail() throws Exception{
        return userMapper.getAllEmail();
    }

    @Override
    public UserDto login(UserDto userDto) throws Exception {
        return userMapper.login(userDto);
    }

    @Override
    public UserDto userInfo(String email) throws Exception {
        return userMapper.userInfo(email);
    }

    @Override
    public void saveRefreshToken(String email, String refreshToken) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("refreshToken", refreshToken);
        userMapper.saveRefreshToken(map);
    }

    @Override
    public Object getRefreshToken(String email) throws Exception {
        return userMapper.getRefreshToken(email);
    }

    @Override
    public void deleteRefreshToken(String email) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("refreshToken", null);
        userMapper.deleteRefreshToken(map);
    }

    @Override
    public void updateProfileImageUrl(String email, String imageUrl) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("url", imageUrl);
        userMapper.updateProfileImageUrl(map);
    }

    @Override
    public String getProfileImageUrl(String email) throws Exception {
        return userMapper.getProfileImageUrl(email);
    }

    @Override
    public boolean checkPassword(String email, String password) throws Exception {
        // 이메일과 비밀번호를 맵에 저장
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        // userMapper를 사용하여 비밀번호 확인
        // 비밀번호가 맞으면 true, 아니면 false 반환
        return userMapper.checkPassword(credentials) != null;
    }

    @Override
    public boolean updateProfile(Map<String, String> userInfo) throws Exception {
        // userMapper를 사용하여 데이터베이스에 프로필 업데이트
        int updateCount = userMapper.updateProfile(userInfo);
        return updateCount > 0;
    }

}
