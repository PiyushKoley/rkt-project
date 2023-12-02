package com.rkt.app.serviceImpl;

import com.rkt.app.convertor.TaskConvertor;
import com.rkt.app.dto.requestDto.TaskDto;
import com.rkt.app.dto.responseDto.TaskResponseDto;
import com.rkt.app.mysql.entity.ProjectEntity;
import com.rkt.app.mysql.entity.TaskEntity;
import com.rkt.app.mysql.entity.UserEntity;
import com.rkt.app.exception.ProjectNotPresentException;
import com.rkt.app.exception.UserNotPresentException;
import com.rkt.app.mysql.repository.ProjectRepository;
import com.rkt.app.mysql.repository.TaskRepository;
import com.rkt.app.mysql.repository.UserRepository;
import com.rkt.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
        UserEntity userEntity = userRepository.findById(taskDto.getUserId())
                .orElseThrow(
                    () -> new UserNotPresentException(
                            String.format("user with user Id : %s not present",taskDto.getUserId())
                    )
                );


        TaskEntity taskEntity = TaskEntity.builder()
                .taskTitle(taskDto.getTaskTitle())
                .taskStatus(taskDto.getTaskStatus())
                .taskDeadline(LocalDate.parse(taskDto.getTaskDeadline()))
                .taskDescription(taskDto.getTaskDescription())
                .projectEntity(projectEntity)
                .assignedUser(userEntity)
                .build();

        //=================Mapping task to app=====================

        Set<TaskEntity> setOfTasks = projectEntity.getTaskEntitySet();

        setOfTasks.add(taskEntity);

        projectEntity.setTaskEntitySet(setOfTasks);

        taskEntity = taskRepository.save(taskEntity);

        //================== Mapping task to user=========================
//        Set<TaskEntity> assignedTaskToUser = userEntity.getSetOfTaskAssigned();
//        assignedTaskToUser.add(taskEntity);
//        userEntity.setSetOfTaskAssigned(assignedTaskToUser);
        userRepository.save(userEntity);
        //===============================================================

        projectRepository.save(projectEntity);

    }

    @Override
    public void deleteTask(long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskResponseDto> getAllTaskOfProject(long projectId) {
        List<TaskEntity> listOfTask = taskRepository.findByProjectEntity_Id(projectId);


        return listOfTask.stream()
                .filter(Objects::nonNull)
                .map(this::getAllFieldsOfTaskResponseDtoFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void updateTask(TaskDto taskDto) {
        long taskId = taskDto.getTaskId();
        TaskEntity taskEntity = taskRepository.findById(taskId)
                    .orElseThrow(
                    () -> new ProjectNotPresentException(String.format("Task with task Id: %s is not present",taskId))
            );

        taskEntity.setTaskDeadline(LocalDate.parse(taskDto.getTaskDeadline()));
        taskEntity.setTaskDescription(taskDto.getTaskDescription());
        taskEntity.setTaskStatus(taskDto.getTaskStatus());
        taskEntity.setTaskTitle(taskDto.getTaskTitle());

        long previousAssigneeId = taskEntity.getAssignedUser().getId();
        long currentAssigneeId = taskDto.getUserId();

        if(previousAssigneeId != currentAssigneeId) {
            taskEntity = changeTaskAssignee(taskEntity,currentAssigneeId);
        }

        taskRepository.save(taskEntity);

    }

    private TaskEntity changeTaskAssignee(TaskEntity taskEntity, long currentAssigneeId) {

        UserEntity currentAssignee = userRepository.findById(currentAssigneeId)
            .orElseThrow(
                    () -> new UserNotPresentException(String.format("user with user Id : %s  not present",currentAssigneeId))
            );

        // ===================== removing previous assignee ================
//        UserEntity previousAssignee = taskEntity.getAssignedUser();
//        Set<TaskEntity>setOfAssignedTask = previousAssignee.getSetOfTaskAssigned();
//
//        setOfAssignedTask.remove(taskEntity);
//
//        previousAssignee.setSetOfTaskAssigned(setOfAssignedTask);
//
//        userRepository.save(previousAssignee);
        //=================================================================

        // adding new assignee....

        taskEntity.setAssignedUser(currentAssignee);
//        Set<TaskEntity> assignedTask = currentAssignee.getSetOfTaskAssigned();
//        assignedTask.add(taskEntity);
//        currentAssignee.setSetOfTaskAssigned(assignedTask);

//        userRepository.save(currentAssignee);

        return taskEntity;

    }

    private TaskResponseDto getAllFieldsOfTaskResponseDtoFromEntity(TaskEntity taskEntity) {

        TaskResponseDto taskResponseDto = TaskConvertor.convertEntityToResponseDto(taskEntity);

        String createdBy = userRepository.getNameOfUser(taskEntity.getCreatedBy());
        taskResponseDto.setCreateByUserName(createdBy);

        if(taskEntity.getUpdatedBy() != null) {
            String updatedBy = userRepository.getNameOfUser(taskEntity.getUpdatedBy());
            taskResponseDto.setUpdatedByUserName(updatedBy);
            taskResponseDto.setUpdatedByUserId(taskEntity.getUpdatedBy());
        }


        return taskResponseDto;

    }



}
