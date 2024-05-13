package com.ssafy.offistellink.model.mapper;

import com.ssafy.offistellink.model.dto.UserDto;

import java.util.List;

public interface UserMapper {
    List<UserDto> selectAll();
}
