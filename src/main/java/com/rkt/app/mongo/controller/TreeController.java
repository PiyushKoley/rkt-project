package com.rkt.app.mongo.controller;

import com.rkt.app.dto.requestDto.MenuDto;
import com.rkt.app.mongo.dto.NodeDto;
import com.rkt.app.mongo.dto.NodeUpdateDto;
import com.rkt.app.mongo.service.NodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menu-view")
public class TreeController {
    @Autowired
    private NodeService nodeService;
    @PostMapping("/add")
    public ResponseEntity<?> addNode(@RequestBody() NodeDto nodeDto) {

        nodeService.addNode(nodeDto);

        return ResponseEntity.ok("menu added successfully...");
    }

    @GetMapping("/get")
    public ResponseEntity<?> getTree() {
        return ResponseEntity.ok(nodeService.getAllNodes());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateNode(@Valid @RequestBody()NodeUpdateDto nodeUpdateDto) {

        nodeService.updateNode(nodeUpdateDto);

        return ResponseEntity.ok("menu updated successfully...");

    }

    @GetMapping("/get-all-name-id")
    public ResponseEntity<?> getAllNodeOption() {
        return ResponseEntity.ok(nodeService.getAllNameId());
    }
}
