package com.rkt.app.mysql.repository;

import com.rkt.app.mysql.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
    @Query("select t from TaskEntity t where t.projectEntity.id = ?1")
    List<TaskEntity> findByProjectEntity_Id(Long id);


}
