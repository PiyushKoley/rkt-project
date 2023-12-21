package com.rkt.app.serviceImpl;

//import com.rkt.app.convertor.TaskConvertor;

import com.rkt.app.dto.requestDto.task.TaskDto;
import com.rkt.app.dto.requestDto.task.TaskUpdateDto;
import com.rkt.app.dto.responseDto.task.TaskCountAndTimeSpendForDateDto;
import com.rkt.app.dto.responseDto.task.TaskResponseDto;
import com.rkt.app.entity.ProjectEntity;
import com.rkt.app.entity.TaskEntity;
import com.rkt.app.entity.UserEntity;
import com.rkt.app.exception.GeneralException;
import com.rkt.app.exception.ProjectNotPresentException;
import com.rkt.app.exception.UserNotPresentException;
import com.rkt.app.repository.ProjectRepository;
import com.rkt.app.repository.TaskRepository;
import com.rkt.app.repository.UserRepository;
import com.rkt.app.security.CustomUserDetails;
import com.rkt.app.service.TaskService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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


    @Override
    public void addNewTask(TaskDto taskDto) {


        long projectId = Long.parseLong(taskDto.getProjectId());

        if(projectId == 0) {
            addNewTaskWithOutProject(taskDto);
            return;
        }

        ProjectEntity projectEntity = projectRepository.findById(projectId).orElseThrow(
                () -> new ProjectNotPresentException(
                        String.format("Project with app id : %s not present.", projectId)
                )
        );

        UserEntity userEntity = getUserFromSecurityContext();


        TaskEntity taskEntity = TaskEntity.builder()
                .taskTitle(taskDto.getTaskTitle())
                .taskDescription(taskDto.getTaskDescription())
                .taskDate(LocalDate.parse(taskDto.getTaskDate()))
                .minutes(taskDto.getMinutes())
                .billable(taskDto.isBillable())
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

    @Override
    public List<TaskResponseDto> getUserTaskByDate(String date) {

        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date);
        }
        catch (DateTimeParseException exception) {
            throw new GeneralException("please provide a valid date ");
        }
        UserEntity userEntity = getUserFromSecurityContext();

        List<TaskEntity> listOfTask = taskRepository.findByAssignedUser_IdAndTaskDate(userEntity.getId(), localDate);

        return listOfTask.stream()
                .map(
                        (taskEntity) -> {

                            var projectEntity = taskEntity.getProjectEntity();

                            return TaskResponseDto.builder()
                                    .taskId(taskEntity.getId())
                                    .taskTitle(taskEntity.getTaskTitle())
                                    .taskDescription(taskEntity.getTaskDescription())
                                    .billable(taskEntity.isBillable() ? "Yes" : "No")
                                    .minutesSpend(taskEntity.getMinutes())
                                    .projectId((projectEntity!=null) ? projectEntity.getId() : 0)
                                    .projectName((projectEntity!= null) ? projectEntity.getProjectName() : "No Project")
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(long taskId) {

        var userEntity = getUserFromSecurityContext();

        TaskEntity task = taskRepository.findById(taskId).orElseThrow(
                ()-> new UserNotPresentException("task does not exist")
        );


        if (task.getAssignedUser().getId() == userEntity.getId()) {

            var projectEntity = task.getProjectEntity();

            projectEntity.getTaskEntitySet().remove(task);
            userEntity.getSetOfTask().remove(task);
            userRepository.save(userEntity);
            projectRepository.save(projectEntity);

            taskRepository.deleteById(taskId);

            return;
        }

        throw new ProjectNotPresentException("you cannot delete this task ");

    }

    @Override
    public List<TaskCountAndTimeSpendForDateDto> getUsersAllTaskCountByDate() {
        UserEntity userEntity = getUserFromSecurityContext();

        Set<TaskEntity> taskEntitySet = userEntity.getSetOfTask();
        Map<LocalDate,List<Integer>> localDateListHashMap = new HashMap<>();

        taskEntitySet.forEach(
                (taskEntity )-> {
                    List<Integer> list = localDateListHashMap.get(taskEntity.getTaskDate());

                    if(list == null ) {
                        list = new ArrayList<>(List.of(0, 0));
                    }

                    int timeSpend = list.get(0) + taskEntity.getMinutes();
                    int taskCount = list.get(1) + 1;

                    list.add(0, timeSpend);
                    list.add(1,taskCount);
                    localDateListHashMap.put(taskEntity.getTaskDate(),list);
                }
        );



        return localDateListHashMap.keySet().stream()
                .map(
                        (localDate) -> {
                            var listOfCountAndTime = localDateListHashMap.get(localDate);

                            return TaskCountAndTimeSpendForDateDto.builder()
                                    .date(localDate.toString())
                                    .timeSpend(listOfCountAndTime.get(0))
                                    .taskCount(listOfCountAndTime.get(1))
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }

    private void addNewTaskWithOutProject(TaskDto taskDto) {
        UserEntity userEntity = getUserFromSecurityContext();


        TaskEntity taskEntity = TaskEntity.builder()
                .taskTitle(taskDto.getTaskTitle())
                .taskDescription(taskDto.getTaskDescription())
                .taskDate(LocalDate.parse(taskDto.getTaskDate()))
                .minutes(taskDto.getMinutes())
                .billable(false)
                .projectEntity(null)
                .assignedUser(userEntity)
                .build();

        taskEntity = taskRepository.save(taskEntity);

        //================== Mapping task to user=========================
        Set<TaskEntity> assignedTaskToUser = userEntity.getSetOfTask();
        assignedTaskToUser.add(taskEntity);
        userEntity.setSetOfTask(assignedTaskToUser);
        userRepository.save(userEntity);
    }

    private UserEntity getUserFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        long userId = customUserDetails.getUserId();

        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotPresentException(
                                String.format("user with user Id : %s not present", userId)
                        )
                );
    }


}
