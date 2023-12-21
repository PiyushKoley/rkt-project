package com.rkt.app.convertor;

import com.rkt.app.dto.responseDto.user.UserDto;
import com.rkt.app.dto.responseDto.user.UserNameIdDto;
import com.rkt.app.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConvertor {

    public static UserDto convertEntityToDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .role(userEntity.getRole().toString())
                .build();
    }

    public static UserNameIdDto convertEntityToUserNameIdDTo(UserEntity userEntity) {
        return UserNameIdDto.builder()
                .userName(userEntity.getName())
                .userId(userEntity.getId())
                .build();
    }
}
