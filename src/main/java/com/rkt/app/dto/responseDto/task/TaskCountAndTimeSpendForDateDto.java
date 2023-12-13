package com.rkt.app.dto.responseDto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskCountAndTimeSpendForDateDto {

    private String date;
    private int taskCount;
    private int timeSpend;
}
