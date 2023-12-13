package com.rkt.app.repository;

import com.rkt.app.entity.ProjectTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTypeRepository extends JpaRepository<ProjectTypeEntity,Long> {
    boolean existsByProjectTypeIgnoreCase(String projectType);
}
