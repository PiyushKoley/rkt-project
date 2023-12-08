package com.rkt.app.service;

import com.rkt.app.dto.requestDto.task.TaskDto;
import com.rkt.app.dto.requestDto.task.TaskUpdateDto;

public interface TaskService {
    void addNewTask(TaskDto taskDto);

    void updateTask(TaskUpdateDto taskUpdateDto);

}
