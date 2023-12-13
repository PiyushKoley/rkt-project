package com.rkt.app.serviceImpl;

import com.rkt.app.convertor.CustomerConvertor;
import com.rkt.app.convertor.ProjectConvertor;
import com.rkt.app.dto.requestDto.project.ProjectDto;
import com.rkt.app.dto.requestDto.project.ProjectUpdateDto;
import com.rkt.app.dto.responseDto.project.PaginationResponseDto;
import com.rkt.app.dto.responseDto.project.ProjectCustomerNameIdDto;
import com.rkt.app.dto.responseDto.project.ProjectResponseDto;
import com.rkt.app.dto.responseDto.task.TaskDateCountDto;
import com.rkt.app.dto.responseDto.user.UserResponseDto;
import com.rkt.app.entity.*;
import com.rkt.app.exception.CustomerNotPresentException;
import com.rkt.app.exception.GeneralException;
import com.rkt.app.exception.ProjectNotPresentException;
import com.rkt.app.exception.UserNotPresentException;
import com.rkt.app.repository.CustomerRepository;
import com.rkt.app.repository.ProjectRepository;
import com.rkt.app.repository.ProjectTypeRepository;
import com.rkt.app.repository.UserRepository;
import com.rkt.app.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectTypeRepository projectTypeRepository;

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
                .projectType(projectDto.getProjectType())
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
                        () -> new ProjectNotPresentException(String.format("app with projectId : %s is not present", projectId))

                );

        projectEntity.setProjectName(projectUpdateDto.getProjectName());
        projectEntity.setProjectType(projectUpdateDto.getProjectType());
        projectEntity.setProjectManager(projectUpdateDto.getProjectManager());
        projectEntity.setProjectDescription(projectUpdateDto.getProjectDescription());

        projectRepository.save(projectEntity);
    }

    @Override
    public List<ProjectCustomerNameIdDto> getAllProjectCustomerNameId() {

        List<ProjectCustomerNameIdDto> getAllPCNameIdDto = new ArrayList<>();

        List<ProjectEntity> listOfProjects = projectRepository.findAll();


        for(ProjectEntity project : listOfProjects) {

            CustomerEntity customerEntity = project.getCustomerEntity();

            ProjectCustomerNameIdDto pcdto = ProjectCustomerNameIdDto.builder()
                    .projectName(project.getProjectName())
                    .projectId(project.getId())
                    .customerName(customerEntity.getCustomerName())
                    .customerCode(customerEntity.getCustomerCode())
                    .build();

            getAllPCNameIdDto.add(pcdto);
        }
        return getAllPCNameIdDto;
    }

    @Override
    public void deleteProject(long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public void linkUserToProject(long userId,long projectId) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(()-> new ProjectNotPresentException("project does not exist"));

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotPresentException("user does not exist"));
        //========================= we set user to a project =========================================

        var setOfUsers = projectEntity.getAssignedUsers();

        setOfUsers.add(userEntity);
        projectEntity.setAssignedUsers(setOfUsers);
        //=========================== we set a project to the user ===================================
        var setOfProjects = userEntity.getProjectEntitySet();

        setOfProjects.add(projectEntity);
        userEntity.setProjectEntitySet(setOfProjects);

        //==============================================================

        userRepository.save(userEntity);
        projectRepository.save(projectEntity);
    }

    @Override
    public List<TaskDateCountDto> getAllDateTaskCount(long projectId) {
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(
                        () -> new ProjectNotPresentException("project with project id : "+ projectId +" is not present")
                );

        Map<LocalDate,Integer> mapOfDateTaskCount = new HashMap<>();
        Set<TaskEntity> taskEntitySet = projectEntity.getTaskEntitySet();

        taskEntitySet.forEach((taskEntity) -> {
            LocalDate date = taskEntity.getTaskDate();
            mapOfDateTaskCount.put(date, mapOfDateTaskCount.getOrDefault(date,0) + 1);
        });

        return mapOfDateTaskCount.keySet().stream()
                .map(
                        (key) -> TaskDateCountDto.builder()
                        .date(key.toString())
                        .count(mapOfDateTaskCount.get(key))
                        .build()
                )
                .collect(Collectors.toList());

    }

    @Override
    public List<?> getAllProjectUsers(long projectId) {

        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(()-> new ProjectNotPresentException("project does not exist."));


        Set<UserEntity> userEntitySet = projectEntity.getAssignedUsers();

        Set<TaskEntity> taskEntitySet = projectEntity.getTaskEntitySet();

        Map<UserEntity,Integer> mapOfCountOfTask = new HashMap<>();

        taskEntitySet.forEach(
                (taskEntity ) -> {
                    var userEntity = taskEntity.getAssignedUser();
                    mapOfCountOfTask.put(userEntity,mapOfCountOfTask.getOrDefault(userEntity,0) + 1);
                }
        );


        return userEntitySet.stream()
                .map(
                        (userEntity) -> UserResponseDto.builder()
                                .userId(userEntity.getId())
                                .name(userEntity.getName())
                                .email(userEntity.getEmail())
                                .projectTaskCount(mapOfCountOfTask.getOrDefault(userEntity,0))
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public void addProjectType(String projectType) {

        if(!StringUtils.hasText(projectType)) {
//            return ResponseEntity.badRequest().body("please provide valid project Type.");
            throw new GeneralException("please provide valid project Type.");
        }

        if(projectTypeRepository.existsByProjectTypeIgnoreCase(projectType)) {
            throw new GeneralException("project type already exists.");
        }
        var projectTypeEntity = ProjectTypeEntity.builder()
                .projectType(projectType)
                .build();

        projectTypeRepository.save(projectTypeEntity);

        System.out.println("inside service project type");
    }

    @Override
    public List<?> getAllProjectType() {

        return projectTypeRepository.findAll();
    }


}

