package com.rkt.app.dto.requestDto.project;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    @NotBlank
    private String projectName;
    @NotBlank
    private String customerName; // ....
    @NotBlank
    private String customerCode; // this we will use to send to frontEnd and get it when user send it to get customer details ....
    @NotBlank
    private String projectDescription;
    @NotBlank
    private String projectManager;
    @NotBlank
    private String projectType; // this field we have as ENUM in our code....
}
