package com.rkt.app.dto.requestDto.task;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

}
