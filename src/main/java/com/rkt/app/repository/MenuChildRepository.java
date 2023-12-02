package com.rkt.app.repository;

import com.rkt.app.entity.MenuChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuChildRepository extends JpaRepository<MenuChildEntity,Long> {
}
