package com.rkt.app.mysql.repository;

import com.rkt.app.mysql.entity.MenuChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuChildRepository extends JpaRepository<MenuChildEntity,Long> {
}
