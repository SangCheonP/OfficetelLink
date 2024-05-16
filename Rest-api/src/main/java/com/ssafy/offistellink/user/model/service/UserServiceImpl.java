package com.ssafy.offistellink.user.model.service;

import com.ssafy.offistellink.user.model.dto.UserDto;
import com.ssafy.offistellink.user.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> getAllUser() throws SQLException{
        return userMapper.getAllUser();
    }

    @Override
    public boolean addUser(UserDto userDto) {
        int result = userMapper.addUser(userDto);
        if(result == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAllEmail() {
        return userMapper.getAllEmail();
    }

    @Override
    public UserDto login(UserDto userDto) throws Exception {
        return null;
    }

    @Override
    public void saveRefreshToken(String email, String refreshToken) throws Exception {

    }
}
