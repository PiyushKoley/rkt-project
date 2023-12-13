package com.rkt.app.service;

import com.rkt.app.dto.requestDto.user.UserDto;
import com.rkt.app.dto.responseDto.project.ProjectNameIdDto;
import com.rkt.app.dto.responseDto.user.UserNameIdDto;

import java.util.List;

public interface UserService {

    void createUser(UserDto userDto) ;

    List<UserNameIdDto> getAllUserNameId();

    List<ProjectNameIdDto> getAllProjectOfUser();

}
