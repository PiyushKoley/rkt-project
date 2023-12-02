package com.rkt.app.service;

import com.rkt.app.dto.requestDto.ProjectDto;
import com.rkt.app.dto.requestDto.ProjectUpdateDto;
import com.rkt.app.dto.responseDto.PaginationResponseDto;
import com.rkt.app.dto.responseDto.ProjectCustomerNameIdDto;
import com.rkt.app.dto.responseDto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {

    public void addNewProject(ProjectDto projectDto);

    public List<ProjectResponseDto> getAllProjects();

    public PaginationResponseDto getProjectsWithPagination(int pageNumber, int pageSize);

    public void updateProject(ProjectUpdateDto projectUpdateDto);

    public List<ProjectCustomerNameIdDto> getAllProjectCustomerNameId();

    void deleteProject(long projectId);

}
