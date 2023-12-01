package com.rkt.demo.repository;

import com.rkt.demo.entity.MenuChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuChildRepository extends JpaRepository<MenuChildEntity,Long> {
}
