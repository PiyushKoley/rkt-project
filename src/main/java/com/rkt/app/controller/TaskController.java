package com.rkt.app.controller;

import com.rkt.app.dto.requestDto.TaskDto;
import com.rkt.app.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add-task")
    public ResponseEntity<?> addNewTask(@Valid @RequestBody() TaskDto taskDto) {
        taskService.addNewTask(taskDto);
        return ResponseEntity.ok("Task added");
    }

    @GetMapping("/get-projects-task/{projectId}")
    public ResponseEntity<?> getAllTaskOfProject(@PathVariable("projectId") long projectId) {
        return ResponseEntity.ok(taskService.getAllTaskOfProject(projectId));
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("taskId") long taskId) {
        taskService.deleteTask(taskId);

        return ResponseEntity.ok("Task deleted successfully...");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTask(@Valid @RequestBody() TaskDto taskDto) {
        taskService.updateTask(taskDto);

        return ResponseEntity.ok("Task updated successfully...");
    }
}
