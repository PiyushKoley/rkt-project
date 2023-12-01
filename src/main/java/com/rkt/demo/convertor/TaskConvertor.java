package com.rkt.demo.convertor;

import com.rkt.demo.dto.responseDto.TaskResponseDto;
import com.rkt.demo.entity.ProjectEntity;
import com.rkt.demo.entity.TaskEntity;
import com.rkt.demo.entity.UserEntity;
import lombok.experimental.UtilityClass;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

@UtilityClass
public class TaskConvertor {

    public TaskResponseDto convertEntityToResponseDto(TaskEntity taskEntity) {

        ProjectEntity projectEntity = taskEntity.getProjectEntity();
        UserEntity userEntity = taskEntity.getAssignedUser();

        return TaskResponseDto.builder()
                .taskId(taskEntity.getId())
                .taskTitle(taskEntity.getTaskTitle())
                .taskStatus(taskEntity.getTaskStatus())
                .taskDeadLine(taskEntity.getTaskDeadline())
                .taskDescription(taskEntity.getTaskDescription())
                .assignedWithProject(projectEntity.getProjectName())
                .assignedUserName(userEntity.getName())
                .assignedUserId(userEntity.getId())
//                .createByUserName()
                .createdByUserId(taskEntity.getCreatedBy())
                .createdAt(taskEntity.getCreatedAt())
//                .updatedByUserName()
//                .updatedByUserId(taskEntity.getUpdatedBy())
                .updatedAt(taskEntity.getUpdatedAt())
                .build();
    }
}
