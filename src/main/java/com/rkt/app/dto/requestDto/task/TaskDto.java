package com.rkt.app.dto.requestDto.task;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    @NotBlank
    private String taskDate;
    @Digits(integer = 3,fraction = 0)
    @Min(0)
    @Max(900)
    private int minutes;

    @NotNull
    private boolean billable;

}
