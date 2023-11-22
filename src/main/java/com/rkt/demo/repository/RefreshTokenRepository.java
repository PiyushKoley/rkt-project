package com.rkt.demo.repository;

import com.rkt.demo.entity.RefreshToken;
import com.rkt.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    RefreshToken findByToken(String token);

    RefreshToken findByUserEntity(UserEntity userEntity);

}
