package com.rkt.demo.serviceImpl;

import com.rkt.demo.convertor.CustomerConvertor;
import com.rkt.demo.convertor.ProjectConvertor;
import com.rkt.demo.dto.requestDto.ProjectDto;
import com.rkt.demo.dto.requestDto.ProjectUpdateDto;
import com.rkt.demo.dto.responseDto.PaginationResponseDto;
import com.rkt.demo.dto.responseDto.ProjectResponseDto;
import com.rkt.demo.entity.CustomerEntity;
import com.rkt.demo.entity.ProjectEntity;
import com.rkt.demo.enums.ProjectType;
import com.rkt.demo.exception.CustomerNotPresentException;
import com.rkt.demo.exception.ProjectNotPresentException;
import com.rkt.demo.repository.CustomerRepository;
import com.rkt.demo.repository.ProjectRepository;
import com.rkt.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
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

        long customerId = customerCode - CustomerConvertor.SOME_FIXED_VALUE;

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
                .map(ProjectConvertor::convertProjectEntityToProjectResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PaginationResponseDto getProjectsWithPagination(int pageNumber, int pageSize) {

        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<ProjectEntity> pages = projectRepository.findAll(p);

        List<ProjectResponseDto> projectResponseDtoList = pages.get()
                                    .filter(Objects::nonNull)
                                    .map(ProjectConvertor::convertProjectEntityToProjectResponseDto)
                                    .toList();

        return PaginationResponseDto.builder()
                .requiredList(projectResponseDtoList)
                .currentPageItemsCount(projectResponseDtoList.size())
                .totalItemsCount(pages.getTotalElements())
                .pageSize(pages.getSize())
                .pageNumber(pages.getNumber())
                .totalPages(pages.getTotalPages())
                .isLastPage(pages.isLast())
                .build();
    }

    @Override
    public void updateProject(ProjectUpdateDto projectUpdateDto) {
        long projectId = Long.parseLong(projectUpdateDto.getProjectId());

        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(
                        () -> new ProjectNotPresentException(String.format("project with projectId : %s is not present", projectId))

                );

        projectEntity.setProjectName(projectUpdateDto.getProjectName());
        projectEntity.setProjectType(ProjectType.valueOf(projectUpdateDto.getProjectType()));
        projectEntity.setProjectManager(projectUpdateDto.getProjectManager());
        projectEntity.setProjectDescription(projectUpdateDto.getProjectDescription());

        projectRepository.save(projectEntity);
    }
}
