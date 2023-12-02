package com.rkt.app.convertor;

import com.rkt.app.dto.responseDto.CustomerNameIdDto;
import com.rkt.app.dto.responseDto.CustomerResponseDto;
import com.rkt.app.mysql.entity.CustomerEntity;
import lombok.experimental.UtilityClass;


@UtilityClass
public class CustomerConvertor {
    public static final long SOME_FIXED_VALUE = 1200000000;


    public static CustomerResponseDto convertCustomerEntityToCustomerResponseDto(CustomerEntity customerEntity) {
        return CustomerResponseDto.builder()
                .customerName(customerEntity.getCustomerName())
                .customerCode(customerEntity.getCustomerCode())
                .contactPerson(customerEntity.getContactPerson())
                .contactEmail(customerEntity.getContactEmail())
                .contactPhone(customerEntity.getContactPhone())
                .build();
    }

    public static CustomerNameIdDto convertCustomerEntityToCustomerNameIdDto(CustomerEntity customerEntity) {
        return CustomerNameIdDto.builder()
                .customerName(customerEntity.getCustomerName())
                .customerCode(customerEntity.getCustomerCode())
                .build();
    }
}
