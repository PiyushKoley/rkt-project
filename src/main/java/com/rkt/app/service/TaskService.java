package com.rkt.app.service;

import com.rkt.app.dto.requestDto.task.TaskDto;
import com.rkt.app.dto.requestDto.task.TaskUpdateDto;

import java.util.List;

public interface TaskService {
    void addNewTask(TaskDto taskDto);

    void updateTask(TaskUpdateDto taskUpdateDto);

    List<?> getUserTaskByDate(String date);

    void deleteTask(long taskId);

}
