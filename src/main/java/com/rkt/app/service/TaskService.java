package com.rkt.app.service;

import com.rkt.app.dto.requestDto.TaskDto;
import com.rkt.app.dto.responseDto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    public void addNewTask(TaskDto taskDto);

    void deleteTask(long taskId);

    List<TaskResponseDto> getAllTaskOfProject(long projectId);


    void updateTask(TaskDto taskDto);

}
