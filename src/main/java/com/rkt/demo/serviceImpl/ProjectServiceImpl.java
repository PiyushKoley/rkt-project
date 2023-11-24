package com.rkt.demo.serviceImpl;

import com.rkt.demo.convertor.CustomerHelper;
import com.rkt.demo.dto.requestDto.ProjectDto;
import com.rkt.demo.dto.responseDto.ProjectResponseDto;
import com.rkt.demo.entity.CustomerEntity;
import com.rkt.demo.entity.ProjectEntity;
import com.rkt.demo.enums.ProjectType;
import com.rkt.demo.exception.CustomerNotPresentException;
import com.rkt.demo.repository.CustomerRepository;
import com.rkt.demo.repository.ProjectRepository;
import com.rkt.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void addNewProject(ProjectDto projectDto) {

        long customerCode = Long.parseLong(projectDto.getCustomerCode());

        long customerId = customerCode - CustomerHelper.SOME_FIXED_VALUE;

        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(
                        () -> new CustomerNotPresentException(
                                String.format("customer with customer code : %s not present.",customerCode)
                        )
                );

        ProjectEntity projectEntity = ProjectEntity.builder()
                .projectName(projectDto.getProjectName())
                .projectDescription(projectDto.getProjectDescription())
                .projectManager(projectDto.getProjectManager())
                .projectType(ProjectType.valueOf(projectDto.getProjectType()))
                .customerEntity(customerEntity)
                .build();

        Set<ProjectEntity> setOfProjects = customerEntity.getProjectEntitySet();

        setOfProjects.add(projectEntity);

        customerEntity.setProjectEntitySet(setOfProjects);

        projectRepository.save(projectEntity);

        customerRepository.save(customerEntity);
    }

    @Override
    public List<ProjectResponseDto> getAllProjects() {

        return projectRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(
                        (projectEntity) -> {

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
                )
                .collect(Collectors.toList());
    }
}
