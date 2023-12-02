package com.rkt.app.mysql.repository;

import com.rkt.app.mysql.entity.MenuParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuParentRepository extends JpaRepository<MenuParentEntity,Long> {
}
