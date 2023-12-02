package com.rkt.app.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private long taskId;
    @NotBlank
    private String taskTitle;
//    @NotBlank
    private String projectId;
    @NotBlank
    private String taskStatus;
    @NotBlank
    private String taskDeadline;
    @NotBlank
    private String taskDescription;

    private long userId;
}
