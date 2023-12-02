package com.rkt.app.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerUpdateDto {

    private String customerName;
    private String customerCode;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
}
