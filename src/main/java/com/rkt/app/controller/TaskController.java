package com.rkt.app.controller;

import com.rkt.app.dto.requestDto.task.TaskDto;
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
    public ResponseEntity<?> addNewTask(@Valid @RequestBody() TaskDto taskDto) throws InterruptedException {

        taskService.addNewTask(taskDto);
        return ResponseEntity.ok("Task added");
    }

    @GetMapping("/get-user-task-by-date")
    public ResponseEntity<?> getUserTaskByDate(@RequestParam("date") String date) {

        return ResponseEntity.ok(taskService.getUserTaskByDate(date));



    }

    @GetMapping("/get-all-task-hour-count-of-user")
    public ResponseEntity<?> getAllUsersTaskCountByDate() {
        return ResponseEntity.ok(taskService.getUsersAllTaskCountByDate());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTask(@RequestParam("taskId") long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("task deleted successfully...");
    }

}
