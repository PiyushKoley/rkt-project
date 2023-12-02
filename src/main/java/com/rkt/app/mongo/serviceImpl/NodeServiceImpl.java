package com.rkt.app.mongo.serviceImpl;

import com.rkt.app.dto.requestDto.MenuDto;
import com.rkt.app.mongo.datastructure.NaryTree;
import com.rkt.app.mongo.dto.NodeDto;
import com.rkt.app.mongo.dto.NodeUpdateDto;
import com.rkt.app.mongo.entity.Node;
import com.rkt.app.mongo.entity.NodePath;
import com.rkt.app.mongo.repository.NodePathRepository;
import com.rkt.app.mongo.repository.NodeRepository;
import com.rkt.app.mongo.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NaryTree naryTree;

    @Autowired
    private NodePathRepository nodePathRepository;

    @Autowired
    private NodeRepository nodeRepository;

    private final String PATH_MAP_ID = "path_map";
    private final String FINAL_TREE_ID = "nary_tree";

    @Override
    public void addNode(NodeDto nodeDto) {

    }

    @Override
    public void updateNode(NodeUpdateDto menuUpdateDto) {

    }

    @Override
    public Node getAllNodes() {


        return null;
    }

    @Override
    public void deleteNode(String nodeId) {

    }

    private boolean isFinalTreePresent() {
        return nodeRepository.count() > 0;
    }

    private boolean isPathMapPresent() {
        return nodePathRepository.count() > 0;
    }

    private Node getTreeFromDatabase() {
        return nodeRepository.findById(FINAL_TREE_ID)
                .orElse(
                        Node.builder()
                                .title("dummyHeadNode")
                                .children(new ArrayList<>())
                                .build()
                );

    }

    private void saveTreeToDatabase(Node dummyHeadNode) {
        dummyHeadNode.setId(FINAL_TREE_ID);
        nodeRepository.save(dummyHeadNode);
    }

    private Map<String, List<?>> getPathMapOfAllNode() {

        NodePath nodePath = nodePathRepository.findById(PATH_MAP_ID)
                .orElse(
                        NodePath.builder()
                                .mapOfEveryNodePath(new HashMap<>())
                                .build()
                );

        return nodePath.getMapOfEveryNodePath();
    }

    private void SavePathMapOfAllNode() {

    }

}
