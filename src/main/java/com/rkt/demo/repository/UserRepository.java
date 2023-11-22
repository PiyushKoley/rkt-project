package com.rkt.demo.repository;

import com.rkt.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);


    UserEntity findByEmail(String username);
}
