package com.rkt.app.dto.responseDto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private long userId;
    private String name;
    private String email;
    private long projectTaskCount;
}
