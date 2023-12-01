package com.rkt.demo.repository;

import com.rkt.demo.entity.MenuParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuParentRepository extends JpaRepository<MenuParentEntity,Long> {
}
