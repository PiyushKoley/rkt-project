package com.rkt.demo.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationResponseDto {

    private List<?> requiredList;

    private int currentPageItemsCount;
    private long totalItemsCount;
    private int pageNumber;
    private int totalPages;
    private int pageSize;
    private boolean isLastPage;
}
