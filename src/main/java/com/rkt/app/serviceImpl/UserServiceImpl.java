package com.rkt.app.serviceImpl;

import com.rkt.app.convertor.UserConvertor;
import com.rkt.app.dto.requestDto.user.UserDto;
import com.rkt.app.dto.responseDto.PaginationResponseDto;
import com.rkt.app.dto.responseDto.project.ProjectNameIdDto;
import com.rkt.app.dto.responseDto.user.UserNameIdDto;
import com.rkt.app.entity.UserEntity;
import com.rkt.app.enums.Role;
import com.rkt.app.exception.EmailAlreadyInUserException;
import com.rkt.app.repository.UserRepository;
import com.rkt.app.security.CustomUserDetails;
import com.rkt.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void createUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyInUserException("email already exist");
        }

        UserEntity userEntity = UserEntity.builder()
                .name(userDto.getName())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .role(Role.valueOf(userDto.getRole().toUpperCase()))
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        userRepository.save(userEntity);
    }

    @Override
    public List<UserNameIdDto> getAllUserNameId() {
        List<UserEntity> listOfAllUsers = userRepository.findAll();

        List<UserNameIdDto> userNameIdDtoList = new ArrayList<>();

        for (UserEntity userEntity : listOfAllUsers) {

            UserNameIdDto nidDto = UserNameIdDto.builder()
                    .userId(userEntity.getId())
                    .userName(userEntity.getName())
                    .build();

            userNameIdDtoList.add(nidDto);
        }
        return userNameIdDtoList;
    }

    @Override
    public List<ProjectNameIdDto> getAllProjectOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        long userId = customUserDetails.getUserId();

        UserEntity userEntity = userRepository.findById(userId).get();

        List<ProjectNameIdDto> list = userEntity.getProjectEntitySet().stream()
                .filter(Objects::nonNull)
                .map(
                        (projectEntity) -> ProjectNameIdDto.builder()
                                .projectId(projectEntity.getId())
                                .projectName(projectEntity.getProjectName())
                        .build()
                )
                .collect(Collectors.toList());

        list.add(0, ProjectNameIdDto.builder()
                            .projectId(0)
                            .projectName("No Project")
                            .build()
        );

        return list;
    }

    @Override
    public PaginationResponseDto getUsersWithPagination(int pageNumber, int pageSize) {

        Pageable p = PageRequest.of(pageNumber,pageSize);

        Page<UserEntity> page = userRepository.findAll(p);

        var listOfUsers = page.stream()
                .map(UserConvertor::convertEntityToDto)
                .collect(Collectors.toList());



        return PaginationResponseDto.builder()
                .requiredList(listOfUsers)
                .currentPageItemsCount(listOfUsers.size())
                .totalPages(page.getTotalPages())
                .totalItemsCount(page.getTotalElements())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .isLastPage(page.isLast())
                .build();
    }

    @Override
    public List<com.rkt.app.dto.responseDto.user.UserDto> searchUserByName(String name) {

        List<UserEntity> listOfUsers = userRepository.findByName(name);


        return listOfUsers.stream()
                .map(UserConvertor::convertEntityToDto)
                .collect(Collectors.toList());
    }

}
