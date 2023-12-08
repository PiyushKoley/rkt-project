package com.rkt.app.serviceImpl;

//import com.rkt.app.convertor.TaskConvertor;
import com.rkt.app.dto.requestDto.task.TaskDto;
import com.rkt.app.dto.requestDto.task.TaskUpdateDto;
import com.rkt.app.entity.ProjectEntity;
import com.rkt.app.entity.TaskEntity;
import com.rkt.app.entity.UserEntity;
import com.rkt.app.exception.ProjectNotPresentException;
import com.rkt.app.exception.UserNotPresentException;
import com.rkt.app.repository.ProjectRepository;
import com.rkt.app.repository.TaskRepository;
import com.rkt.app.repository.UserRepository;
import com.rkt.app.security.CustomUserDetails;
import com.rkt.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private CustomerRepository customerRepository;


    @Override
    public void addNewTask(TaskDto taskDto) {


        long projectId= Long.parseLong(taskDto.getProjectId());


        ProjectEntity projectEntity = projectRepository.findById(projectId).orElseThrow(
                () -> new ProjectNotPresentException(
                        String.format("Project with app id : %s not present.",projectId)
                )
        );

        UserEntity userEntity = getUserFromSecurityContext();


        TaskEntity taskEntity = TaskEntity.builder()
                .taskTitle(taskDto.getTaskTitle())
                .taskDescription(taskDto.getTaskDescription())
                .projectEntity(projectEntity)
                .assignedUser(userEntity)
                .build();

        //=================Mapping task to project=====================

        Set<TaskEntity> setOfTasks = projectEntity.getTaskEntitySet();

        setOfTasks.add(taskEntity);

        projectEntity.setTaskEntitySet(setOfTasks);

        taskEntity = taskRepository.save(taskEntity);

        //================== Mapping task to user=========================
        Set<TaskEntity> assignedTaskToUser = userEntity.getSetOfTask();
        assignedTaskToUser.add(taskEntity);
        userEntity.setSetOfTask(assignedTaskToUser);
        userRepository.save(userEntity);
        //===============================================================

        projectRepository.save(projectEntity);

    }

    @Override
    public void updateTask(TaskUpdateDto taskUpdateDto) {

    }

    private UserEntity getUserFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        long userId =  customUserDetails.getUserId();

        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotPresentException(
                                String.format("user with user Id : %s not present",userId)
                        )
                );
    }



}
