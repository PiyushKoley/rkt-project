package com.rkt.app.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectCustomerNameIdDto {
    private String customerName;
    private long customerCode;
    private String projectName;
    private long projectId;

}
