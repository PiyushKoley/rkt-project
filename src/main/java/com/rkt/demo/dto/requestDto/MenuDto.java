package com.rkt.demo.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDto {

    private String title;
    private String path;
    private String icon;

//    private List<MenuChildDto> subNav;
    private long parentId;
}
