package com.rkt.app.repository;

import com.rkt.app.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    boolean existsByContactEmailAndContactPhone(String contactEmail, String contactPhone);

}
