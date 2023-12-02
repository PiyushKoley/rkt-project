package com.rkt.app.convertor;

import com.rkt.app.dto.responseDto.TaskResponseDto;
import com.rkt.app.mysql.entity.ProjectEntity;
import com.rkt.app.mysql.entity.TaskEntity;
import com.rkt.app.mysql.entity.UserEntity;
import lombok.experimental.UtilityClass;

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
