package com.ssafy.offistellink.user.model.mapper;

import com.ssafy.offistellink.user.model.dto.UserDto;

import java.util.List;

public interface UserMapper {
    List<UserDto> selectAll();
}
