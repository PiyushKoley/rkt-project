package com.rkt.app.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
