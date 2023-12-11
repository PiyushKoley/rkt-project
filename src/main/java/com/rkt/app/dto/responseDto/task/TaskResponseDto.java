package com.rkt.app.dto.responseDto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponseDto {
    private long taskId;
    private String taskTitle;
    private String taskDescription;
    private int minutesSpend;
    private long projectId;
    private String projectName;
}
