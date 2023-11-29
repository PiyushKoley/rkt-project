package com.rkt.demo.service;

import com.rkt.demo.dto.requestDto.ProjectDto;
import com.rkt.demo.dto.requestDto.ProjectUpdateDto;
import com.rkt.demo.dto.responseDto.PaginationResponseDto;
import com.rkt.demo.dto.responseDto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {

    public void addNewProject(ProjectDto projectDto);

    public List<ProjectResponseDto> getAllProjects();

    public PaginationResponseDto getProjectsWithPagination(int pageNumber, int pageSize);

    public void updateProject(ProjectUpdateDto projectUpdateDto);
}
