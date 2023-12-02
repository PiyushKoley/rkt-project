package com.rkt.app.mongo.controller;

import com.rkt.app.dto.requestDto.MenuDto;
import com.rkt.app.mongo.dto.NodeDto;
import com.rkt.app.mongo.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu-tree")
public class TreeController {
    @Autowired
    private NodeService nodeService;
    @PostMapping("/add")
    public String addNode(@RequestBody() NodeDto nodeDto) {

        nodeService.addNode(nodeDto);

        return "created....";
    }
}
