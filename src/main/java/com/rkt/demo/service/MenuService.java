package com.rkt.demo.service;

import com.rkt.demo.dto.requestDto.MenuDto;
import com.rkt.demo.dto.responseDto.MenuParentIdTitleDto;
import com.rkt.demo.entity.MenuParentEntity;

import java.util.List;

public interface MenuService {

    public void addMenu(MenuDto menuDto);

    List<MenuParentEntity> getAllMenu();

    List<MenuParentIdTitleDto> getAllParentMenuId();

    void deleteParentMenu(long parentId);

    void deleteChildMenu(long childId);
}
