package com.rkt.demo.controller;

import com.rkt.demo.dto.requestDto.CustomerDto;
import com.rkt.demo.dto.requestDto.CustomerUpdateDto;
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

        return ResponseEntity.ok("customer added successfully.");
    }

    @GetMapping("/get-all-name-id")
    public ResponseEntity<?> getAllCustomerNameIdDto() {
        return ResponseEntity.ok(customerService.getAllCustomerNameIdDto());
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }


    @GetMapping("/get")
    public ResponseEntity<?> getCustomersWithPagination(@RequestParam(value = "pageNumber",defaultValue = "0") int pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
                                                        ) {
        return ResponseEntity.ok(customerService.getCustomersWithPagination(pageNumber,pageSize));
    }

    @PutMapping("/update-customer")
    public ResponseEntity<?> updateCustomer(@RequestBody() CustomerUpdateDto customerUpdateDto){
        return ResponseEntity.ok(customerService.updateCustomer(customerUpdateDto));
    }
}
