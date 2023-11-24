package com.rkt.demo.service;

import com.rkt.demo.dto.requestDto.ProjectDto;
import com.rkt.demo.dto.responseDto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {

    public void addNewProject(ProjectDto projectDto);

    public List<ProjectResponseDto> getAllProjects();
}
