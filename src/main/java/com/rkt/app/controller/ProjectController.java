package com.rkt.app.controller;

import com.rkt.app.dto.requestDto.project.ProjectDto;
import com.rkt.app.dto.requestDto.project.ProjectUpdateDto;
import com.rkt.app.service.ProjectService;
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
        return ResponseEntity.ok("app deleted successfully...");
    }

    @PostMapping("/link-user")
    public ResponseEntity<?> linkAUserToProject(@RequestParam("userId") long userId,
                                                @RequestParam("projectId") long projectId ) {

        projectService.linkUserToProject(userId,projectId);

        return ResponseEntity.ok("user is linked to project successfully...");
    }

    @GetMapping("/get-all-date-task-count")
    public ResponseEntity<?> getAllDateTaskCount(@RequestParam("projectId") long projectId) {
        return ResponseEntity.ok(projectService.getAllDateTaskCount(projectId));
    }

    @GetMapping("/get-all-users-of-project")
    public ResponseEntity<?> getAllUserOfProject(@RequestParam("projectId")long projectId) {
        return ResponseEntity.ok(projectService.getAllProjectUsers(projectId));
    }
}
