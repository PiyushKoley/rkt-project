package com.rkt.demo.controller;

import com.rkt.demo.dto.requestDto.ProjectDto;
import com.rkt.demo.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/add-project")
    public ResponseEntity<?> addNewProject(@Valid @RequestBody()ProjectDto projectDto) {
        projectService.addNewProject(projectDto);

        return ResponseEntity.ok("Project created...");
    }
}
