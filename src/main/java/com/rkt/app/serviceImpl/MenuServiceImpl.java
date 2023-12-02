package com.rkt.app.serviceImpl;

import com.rkt.app.dto.requestDto.MenuDto;
import com.rkt.app.dto.responseDto.MenuParentIdTitleDto;
import com.rkt.app.entity.MenuChildEntity;
import com.rkt.app.entity.MenuParentEntity;
import com.rkt.app.exception.MenuNotPresentException;
import com.rkt.app.repository.MenuChildRepository;
import com.rkt.app.repository.MenuParentRepository;
import com.rkt.app.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuParentRepository menuParentRepository;
    @Autowired
    private MenuChildRepository menuChildRepository;

    @Override
    public void addMenu(MenuDto menuDto) {
        long parentId = menuDto.getParentId();
        // for Menu parent...
        if(parentId == 0) {
            MenuParentEntity menuParentEntity = MenuParentEntity.builder()
                    .title(menuDto.getTitle())
                    .path(menuDto.getPath())
                    .icon(menuDto.getIcon())
                    .build();

            menuParentRepository.save(menuParentEntity);
            return;
        }

        // if request is for child ....

        var parentMenu = menuParentRepository.findById(parentId).orElseThrow(()->new MenuNotPresentException("parent menu not present"));

        MenuChildEntity childMenu = MenuChildEntity.builder()
                .title(menuDto.getTitle())
                .path(menuDto.getPath())
                .icon(menuDto.getIcon())
                .parentMenu(parentMenu)
                .build();

        menuChildRepository.save(childMenu);
    }

    @Override
    public List<MenuParentEntity> getAllMenu() {

        return menuParentRepository.findAll();
    }

    @Override
    public List<MenuParentIdTitleDto> getAllParentMenuId() {
        return menuParentRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(
                        (menuParentEntity)-> MenuParentIdTitleDto.builder()
                                .parentId(menuParentEntity.getId())
                                .title(menuParentEntity.getTitle())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public void deleteParentMenu(long parentId) {
        menuParentRepository.deleteById(parentId);
    }

    @Override
    public void deleteChildMenu(long childId) {
        menuChildRepository.deleteById(childId);
    }


}
