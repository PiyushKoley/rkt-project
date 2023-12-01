package com.rkt.demo.repository;

import com.rkt.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);


    UserEntity findByEmail(String username);

    @Query("select u.name from UserEntity u where u.id = ?1")
    String getNameOfUser(long id);
}
