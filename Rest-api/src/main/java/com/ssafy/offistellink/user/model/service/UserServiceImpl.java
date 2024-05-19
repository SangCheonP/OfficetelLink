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
}
