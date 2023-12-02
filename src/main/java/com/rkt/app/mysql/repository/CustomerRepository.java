package com.rkt.app.mysql.repository;

import com.rkt.app.mysql.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    boolean existsByContactEmailAndContactPhone(String contactEmail, String contactPhone);

}
