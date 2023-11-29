package com.rkt.demo.dto.requestDto;

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
    private String customerCode;
    @NotBlank
    private String taskStatus;
    @NotBlank
    private String taskDeadline;
    private String taskDescription;
    @NotBlank
    private long userId;
}
