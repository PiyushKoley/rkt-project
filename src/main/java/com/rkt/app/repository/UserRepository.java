package com.rkt.app.repository;

import com.rkt.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);


    UserEntity findByEmail(String username);

    @Query("select u.name from UserEntity u where u.id = ?1")
    String getNameOfUser(long id);

    List<UserEntity> findByName(String name);

    @Query(value =
            """
                select * from users
                where id not in (select user_id FROM user_assigned_project
                where project_id=?1)
            """,nativeQuery = true)
    List<UserEntity> getAllUsersNotAssignedToProject(long projectId);
}
