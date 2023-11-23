package com.rkt.demo.repository;

import com.rkt.demo.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
    boolean existsByContactEmailAndContactPhone(String contactEmail, String contactPhone);

}
