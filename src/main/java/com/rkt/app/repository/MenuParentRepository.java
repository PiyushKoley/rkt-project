package com.rkt.app.repository;

import com.rkt.app.entity.MenuParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuParentRepository extends JpaRepository<MenuParentEntity,Long> {
}
