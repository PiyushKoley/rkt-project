package com.rkt.app.dto.responseDto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectNameIdDto {

    private long projectId;
    private String projectName;
}
