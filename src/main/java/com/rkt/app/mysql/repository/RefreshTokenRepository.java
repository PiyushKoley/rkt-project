package com.rkt.app.mysql.repository;

import com.rkt.app.mysql.entity.RefreshToken;
import com.rkt.app.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    RefreshToken findByToken(String token);

    RefreshToken findByUserEntity(UserEntity userEntity);

}
