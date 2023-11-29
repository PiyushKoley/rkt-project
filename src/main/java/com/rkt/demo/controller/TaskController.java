package com.rkt.demo.controller;

import com.rkt.demo.dto.requestDto.TaskDto;
import com.rkt.demo.service.TaskService;
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

    @GetMapping("/get-all-name-id")
    public ResponseEntity<?> getAllProjectCustomerNameIdDto() {
        return ResponseEntity.ok(taskService.getAllProjectCustomerNameIdDto());
    }
}
