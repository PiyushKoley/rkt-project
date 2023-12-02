package com.rkt.app.service;

import com.rkt.app.dto.requestDto.UserDto;
import com.rkt.app.dto.responseDto.UserNameIdDto;

import java.util.List;

public interface UserService {

    public void createUser(UserDto userDto) ;

    public List<UserNameIdDto> getAllUserNameId();

}
