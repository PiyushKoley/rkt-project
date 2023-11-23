package com.rkt.demo.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {

    private String customerName;
    private long customerCode;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
}
