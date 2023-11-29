package com.rkt.demo.serviceImpl;

import com.rkt.demo.dto.requestDto.TaskDto;
import com.rkt.demo.dto.responseDto.ProjectCustomerNameIdDto;
import com.rkt.demo.entity.CustomerEntity;
import com.rkt.demo.entity.ProjectEntity;
import com.rkt.demo.entity.TaskEntity;
import com.rkt.demo.exception.ProjectNotPresentException;
import com.rkt.demo.repository.CustomerRepository;
import com.rkt.demo.repository.ProjectRepository;
import com.rkt.demo.repository.TaskRepository;
import com.rkt.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void addNewTask(TaskDto taskDto) {

        long projectId= Long.parseLong(taskDto.getProjectId());


        ProjectEntity projectEntity = projectRepository.findById(projectId).orElseThrow(
                () -> new ProjectNotPresentException(
                        String.format("Project with project id : %s not present.",projectId)
                )
        );

        TaskEntity taskEntity = TaskEntity.builder()
                .taskTitle(taskDto.getTaskTitle())
                .taskStatus(taskDto.getTaskStatus())
                .taskDeadline(LocalDate.parse(taskDto.getTaskDeadline()))
                .taskDescription(taskDto.getTaskDescription())
                .assignee(taskDto.getUserId())
                .projectEntity(projectEntity)
                .build();

        Set<TaskEntity> setOfTasks = projectEntity.getTaskEntitySet();

        setOfTasks.add(taskEntity);

        projectEntity.setTaskEntitySet(setOfTasks);

        taskRepository.save(taskEntity);
        projectRepository.save(projectEntity);

    }

    @Override
    public List<ProjectCustomerNameIdDto> getAllProjectCustomerNameIdDto() {

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

}
