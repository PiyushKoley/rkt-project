package com.rkt.app.service;

import com.rkt.app.dto.requestDto.project.ProjectDto;
import com.rkt.app.dto.requestDto.project.ProjectUpdateDto;
import com.rkt.app.dto.responseDto.project.PaginationResponseDto;
import com.rkt.app.dto.responseDto.project.ProjectCustomerNameIdDto;
import com.rkt.app.dto.responseDto.project.ProjectResponseDto;
import com.rkt.app.dto.responseDto.task.TaskDateCountDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ProjectService {

    public void addNewProject(ProjectDto projectDto);

    public List<ProjectResponseDto> getAllProjects();

    public PaginationResponseDto getProjectsWithPagination(int pageNumber, int pageSize);

    public void updateProject(ProjectUpdateDto projectUpdateDto);

    public List<ProjectCustomerNameIdDto> getAllProjectCustomerNameId();

    void deleteProject(long projectId);
    void linkUserToProject(long userId, long projectId);

    List<TaskDateCountDto> getAllDateTaskCount(long projectId);

    List<?> getAllProjectUsers(long projectId);
}
