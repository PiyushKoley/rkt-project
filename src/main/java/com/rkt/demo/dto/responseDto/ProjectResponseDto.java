package com.rkt.demo.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponseDto {

    private long projectCode;
    private String projectName;
    private String projectDescription;
    private String projectManager;

    private String projectType;

    private String customerName;
    private long customerCode;
}
