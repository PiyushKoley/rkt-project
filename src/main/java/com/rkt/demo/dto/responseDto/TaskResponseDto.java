package com.rkt.demo.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponseDto {
    private long taskId;
    private String taskTitle;
    private String taskStatus;
    private LocalDate taskDeadLine;
    private String taskDescription;
    private String assignedWithProject;
    //=====================================
    private String assignedUserName;
    private long assignedUserId;
    //==================================
    private String createByUserName;
    private long createdByUserId;
    private LocalDate createdAt;
    //========================================
    private String updatedByUserName;
    private long updatedByUserId;
    private Date updatedAt;
}
