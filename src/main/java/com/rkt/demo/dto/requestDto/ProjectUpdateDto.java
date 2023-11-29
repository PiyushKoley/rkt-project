package com.rkt.demo.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectUpdateDto {
    private String projectId;
    private String projectName;
    private String projectManager;
    private String projectType;
//    private String customerCode;
//    private String customerName;
    private String projectDescription;

}
