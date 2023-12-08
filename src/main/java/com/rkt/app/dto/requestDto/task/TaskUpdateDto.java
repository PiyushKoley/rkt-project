package com.rkt.app.dto.requestDto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateDto {

    private long taskId;
    private String taskTitle;
    private String taskDescription;
}
