package com.rkt.app.mongo.service;

import com.rkt.app.dto.requestDto.MenuDto;
import com.rkt.app.mongo.dto.NodeDto;
import com.rkt.app.mongo.dto.NodeUpdateDto;
import com.rkt.app.mongo.entity.Node;
import com.rkt.app.mongo.entity.NodePath;

import java.util.List;

public interface NodeService {

    void addNode(NodeDto nodeDto);

    void updateNode(NodeUpdateDto menuUpdateDto);

    List<Node> getAllNodes();

    void deleteNode(String nodeId);

    List<NodePath> getAllNameId();
}
