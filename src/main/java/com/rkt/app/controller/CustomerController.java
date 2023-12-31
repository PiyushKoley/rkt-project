package com.rkt.app.controller;

import com.rkt.app.dto.requestDto.customer.CustomerDto;
import com.rkt.app.dto.requestDto.customer.CustomerUpdateDto;
import com.rkt.app.service.CustomerService;
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

    @DeleteMapping("/delete/{customerCode}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerCode") long customerCode) {
        customerService.deleteCustomer(customerCode);
        return ResponseEntity.ok("Customer deleted successfully...");
    }
}
