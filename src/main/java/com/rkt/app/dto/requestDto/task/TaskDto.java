package com.rkt.app.dto.requestDto.task;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @NotBlank
    private String taskTitle;
    @NotBlank
    private String projectId;
    @NotBlank
    private String taskDescription;

}
