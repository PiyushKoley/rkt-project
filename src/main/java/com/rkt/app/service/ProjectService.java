package com.rkt.app.service;

import com.rkt.app.dto.requestDto.project.ProjectDto;
import com.rkt.app.dto.requestDto.project.ProjectUpdateDto;
import com.rkt.app.dto.responseDto.PaginationResponseDto;
import com.rkt.app.dto.responseDto.project.ProjectCustomerNameIdDto;
import com.rkt.app.dto.responseDto.project.ProjectResponseDto;
import com.rkt.app.dto.responseDto.task.TaskDateCountDto;

import java.util.List;

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

    void addProjectType(String projectType);

    List<?> getAllProjectType();

    List<?> getAllUsersNameIdNotAssignedToProject(long projectId);
}
