package com.rkt.demo.controller;

import com.rkt.demo.dto.requestDto.CustomerDto;
import com.rkt.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody()CustomerDto customerDto) {

        customerService.addNewCustomer(customerDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-all-name-id")
    public ResponseEntity<?> getAllCustomerNameIdDto() {
        return ResponseEntity.ok(customerService.getAllCustomerNameIdDto());
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }


}
