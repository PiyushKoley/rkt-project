package com.rkt.demo.service;

import com.rkt.demo.dto.requestDto.UserDto;
import com.rkt.demo.dto.responseDto.UserNameIdDto;

import java.util.List;

public interface UserService {

    public void createUser(UserDto userDto) ;

    public List<UserNameIdDto> getAllUserNameId();

}
