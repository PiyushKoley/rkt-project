package com.rkt.app.mongo.service;

import com.rkt.app.dto.requestDto.MenuDto;
import com.rkt.app.mongo.dto.NodeDto;
import com.rkt.app.mongo.dto.NodeUpdateDto;
import com.rkt.app.mongo.entity.Node;

public interface NodeService {

    public void addNode(NodeDto nodeDto);

    public void updateNode(NodeUpdateDto menuUpdateDto);

    public Node getAllNodes();

    public void deleteNode(String nodeId);
}
