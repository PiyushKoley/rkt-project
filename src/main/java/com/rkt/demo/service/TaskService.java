package com.rkt.demo.service;

import com.rkt.demo.dto.requestDto.TaskDto;
import com.rkt.demo.dto.responseDto.ProjectCustomerNameIdDto;
import com.rkt.demo.dto.responseDto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    public void addNewTask(TaskDto taskDto);

    void deleteTask(long taskId);

    List<TaskResponseDto> getAllTaskOfProject(long projectId);


    void updateTask(TaskDto taskDto);

}
