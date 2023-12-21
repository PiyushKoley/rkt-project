package com.rkt.app.service;

import com.rkt.app.dto.requestDto.customer.CustomerDto;
import com.rkt.app.dto.requestDto.customer.CustomerUpdateDto;
import com.rkt.app.dto.responseDto.customer.CustomerNameIdDto;
import com.rkt.app.dto.responseDto.PaginationResponseDto;
import com.rkt.app.dto.responseDto.customer.CustomerResponseDto;

import java.util.List;

public interface CustomerService {

    public void addNewCustomer(CustomerDto customerDto);

    public List<CustomerNameIdDto> getAllCustomerNameIdDto();

    public List<CustomerResponseDto> getAllCustomers();

    public PaginationResponseDto getCustomersWithPagination(int pageNumber, int pageSize);

    public CustomerResponseDto updateCustomer(CustomerUpdateDto customerUpdateDto);

    public void deleteCustomer(long customerCode);
}
