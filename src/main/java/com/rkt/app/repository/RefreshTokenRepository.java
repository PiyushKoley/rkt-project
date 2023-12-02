package com.rkt.app.repository;

import com.rkt.app.entity.RefreshToken;
import com.rkt.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    RefreshToken findByToken(String token);

    RefreshToken findByUserEntity(UserEntity userEntity);

}
