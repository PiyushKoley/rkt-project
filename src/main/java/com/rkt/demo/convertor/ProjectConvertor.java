package com.rkt.demo.convertor;

import com.rkt.demo.dto.responseDto.ProjectResponseDto;
import com.rkt.demo.entity.CustomerEntity;
import com.rkt.demo.entity.ProjectEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProjectConvertor {

    public static ProjectResponseDto convertProjectEntityToProjectResponseDto(ProjectEntity projectEntity) {

        CustomerEntity customerEntity = projectEntity.getCustomerEntity();

        return ProjectResponseDto.builder()
                .projectCode(projectEntity.getId())
                .projectName(projectEntity.getProjectName())
                .projectType(projectEntity.getProjectType().toString())
                .projectDescription(projectEntity.getProjectDescription())
                .projectManager(projectEntity.getProjectManager())
                .customerCode(customerEntity.getCustomerCode())
                .customerName(customerEntity.getCustomerName())
                .build();
    }
}
