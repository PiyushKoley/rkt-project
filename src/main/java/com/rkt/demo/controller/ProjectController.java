package com.rkt.demo.controller;

import com.rkt.demo.dto.requestDto.ProjectDto;
import com.rkt.demo.dto.requestDto.ProjectUpdateDto;
import com.rkt.demo.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getProjectsWithPagination(@RequestParam(value = "pageNumber",defaultValue = "0") int pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        return ResponseEntity.ok(projectService.getProjectsWithPagination(pageNumber, pageSize));
    }

    @PutMapping("/update-project")
    public ResponseEntity<?> updateProject(@RequestBody() ProjectUpdateDto projectUpdateDto){
        projectService.updateProject(projectUpdateDto);
        return ResponseEntity.ok("Project Updated...");
    }

    @GetMapping("/get-all-name-id")
    public ResponseEntity<?> getAllProjectCustomerNameIdDto() {
        return ResponseEntity.ok(projectService.getAllProjectCustomerNameId());
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable("projectId") long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok("project deleted successfully...");
    }
}
