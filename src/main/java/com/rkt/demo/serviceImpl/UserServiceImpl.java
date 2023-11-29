package com.rkt.demo.serviceImpl;

import com.rkt.demo.dto.requestDto.UserDto;
import com.rkt.demo.entity.UserEntity;
import com.rkt.demo.enums.Role;
import com.rkt.demo.exception.EmailAlreadyInUserException;
import com.rkt.demo.repository.UserRepository;
import com.rkt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .role(Role.valueOf(userDto.getRole()))
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        userRepository.save(userEntity);
    }

}
