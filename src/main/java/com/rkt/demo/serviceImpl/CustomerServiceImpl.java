package com.rkt.demo.serviceImpl;

import com.rkt.demo.convertor.CustomerConvertor;
import com.rkt.demo.dto.requestDto.CustomerDto;
import com.rkt.demo.dto.responseDto.CustomerNameIdDto;
import com.rkt.demo.dto.responseDto.PaginationResponseDto;
import com.rkt.demo.dto.responseDto.CustomerResponseDto;
import com.rkt.demo.entity.CustomerEntity;
import com.rkt.demo.exception.CustomerAlreadyExistException;
import com.rkt.demo.repository.CustomerRepository;
import com.rkt.demo.repository.UserRepository;
import com.rkt.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.rkt.demo.convertor.CustomerConvertor.SOME_FIXED_VALUE;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addNewCustomer(CustomerDto customerDto) {

        String email = customerDto.getContactEmail().toLowerCase();
        String phone = customerDto.getContactPhoneNumber();

        if(customerRepository.existsByContactEmailAndContactPhone(email,phone)) {
            throw new CustomerAlreadyExistException("customer with given email and phone already exists.");
        }


        CustomerEntity customerEntity = CustomerEntity.builder()
                .customerName(customerDto.getCustomerName())
                .contactPerson(customerDto.getContactPerson())
                .contactEmail(email)
                .contactPhone(phone)
                .build();

        customerEntity = customerRepository.save(customerEntity);

        // setting customer code ....
        customerEntity.setCustomerCode(SOME_FIXED_VALUE + customerEntity.getId());

        customerRepository.save(customerEntity);

    }

    @Override
    public List<CustomerNameIdDto> getAllCustomerNameIdDto() {
        List<CustomerEntity> listOfAllCustomers = customerRepository.findAll();

        return listOfAllCustomers.stream()
                .filter(Objects::nonNull)
                .map(CustomerConvertor::convertCustomerEntityToCustomerNameIdDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(CustomerConvertor::convertCustomerEntityToCustomerResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaginationResponseDto getCustomersWithPagination(int pageNumber, int pageSize) {

        Pageable p = PageRequest.of(pageNumber, pageSize);

        Page<CustomerEntity> pages = customerRepository.findAll(p);


        List<CustomerResponseDto> customersList = pages.get()
                                .filter(Objects::nonNull)
                                .map(CustomerConvertor::convertCustomerEntityToCustomerResponseDto)
                                .toList();


        return PaginationResponseDto.builder()
                .requiredList(customersList)
                .currentPageItemsCount(customersList.size())
                .totalItemsCount(pages.getTotalElements())
                .pageSize(pages.getSize())
                .pageNumber(pages.getNumber())
                .totalPages(pages.getTotalPages())
                .isLastPage(pages.isLast())
                .build();

    }


}
