package com.rkt.demo.serviceImpl;

import com.rkt.demo.convertor.CustomerHelper;
import com.rkt.demo.dto.requestDto.CustomerDto;
import com.rkt.demo.dto.responseDto.CustomerNameIdDto;
import com.rkt.demo.dto.responseDto.CustomerResponseDto;
import com.rkt.demo.entity.CustomerEntity;
import com.rkt.demo.exception.CustomerAlreadyExistException;
import com.rkt.demo.repository.CustomerRepository;
import com.rkt.demo.repository.UserRepository;
import com.rkt.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.rkt.demo.convertor.CustomerHelper.SOME_FIXED_VALUE;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addNewCustomer(CustomerDto customerDto) {

        String email = customerDto.getCustomerName().toLowerCase();
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
        return getListOfAllCustomersNameAndId();
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(
                        customerEntity -> CustomerResponseDto.builder()
                            .customerName(customerEntity.getCustomerName())
                            .customerCode(customerEntity.getCustomerCode())
                            .contactPerson(customerEntity.getContactPerson())
                            .contactEmail(customerEntity.getContactEmail())
                            .contactPhone(customerEntity.getContactPhone())
                            .build()
                )
                .collect(Collectors.toList());
    }

    private List<CustomerNameIdDto> getListOfAllCustomersNameAndId() {


        List<CustomerEntity> listOfAllCustomers = customerRepository.findAll();

        return listOfAllCustomers.stream()
                .filter(Objects::nonNull)
                .map(
                        (customerEntity) -> {
                            return CustomerNameIdDto.builder()
                                    .customerName(customerEntity.getCustomerName())
                                    .customerCode(customerEntity.getCustomerCode())
                                    .build();

                        }
                ).collect(Collectors.toList());
    }


}
