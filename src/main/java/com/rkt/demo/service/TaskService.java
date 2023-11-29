package com.rkt.demo.service;

import com.rkt.demo.dto.requestDto.TaskDto;
import com.rkt.demo.dto.responseDto.ProjectCustomerNameIdDto;

import java.util.List;

public interface TaskService {
    public void addNewTask(TaskDto taskDto);

    public List<ProjectCustomerNameIdDto> getAllProjectCustomerNameIdDto();
}
