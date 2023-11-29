package com.rkt.demo.service;

import com.rkt.demo.dto.requestDto.CustomerDto;
import com.rkt.demo.dto.responseDto.CustomerNameIdDto;
import com.rkt.demo.dto.responseDto.PaginationResponseDto;
import com.rkt.demo.dto.responseDto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    public void addNewCustomer(CustomerDto customerDto);

    public List<CustomerNameIdDto> getAllCustomerNameIdDto();

    public List<CustomerResponseDto> getAllCustomers();

    public PaginationResponseDto getCustomersWithPagination(int pageNumber, int pageSize);
}
