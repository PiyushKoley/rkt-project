package com.rkt.demo.dto.requestDto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotBlank
    private String customerName;
    @NotBlank
    private String contactPerson;
    @NotBlank
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String contactEmail;
    @NotBlank
    @Digits(integer = 10,fraction = 0)
    @Length(min = 10,max=10,message = "length of phone number should be 10")
    private String contactPhoneNumber;
}
