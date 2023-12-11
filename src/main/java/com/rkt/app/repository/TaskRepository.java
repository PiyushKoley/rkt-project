package com.rkt.app.repository;

import com.rkt.app.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
    List<TaskEntity> findByAssignedUser_IdAndTaskDate(Long id, LocalDate taskDate);
    @Query("select t from TaskEntity t where t.projectEntity.id = ?1")
    List<TaskEntity> findByProjectEntity_Id(Long id);


}
