package com.rkt.demo.dto.requestDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "name cannot be null")
    private String name;
    @Digits(integer = 2,fraction = 0)
    @Min(18)
    @Max(80)
    private int age;
    @NotBlank(message = "name cannot be null")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    @NotBlank(message = "please provide 'ADMIN' or 'USER' role.")
    private String role;
    @NotBlank(message = "name cannot be null")
    private String password;

}
