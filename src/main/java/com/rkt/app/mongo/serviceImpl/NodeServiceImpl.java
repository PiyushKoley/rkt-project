package com.rkt.app.mongo.serviceImpl;

import com.rkt.app.mongo.datastructure.NaryTree;
import com.rkt.app.mongo.datastructure.NodeNotFoundException;
import com.rkt.app.mongo.dto.NodeDto;
import com.rkt.app.mongo.dto.NodeUpdateDto;
import com.rkt.app.mongo.entity.Node;
import com.rkt.app.mongo.entity.NodePath;
import com.rkt.app.mongo.repository.NodePathRepository;
import com.rkt.app.mongo.repository.NodeRepository;
import com.rkt.app.mongo.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NaryTree naryTree;

    @Autowired
    private NodePathRepository nodePathRepository;

    @Autowired
    private NodeRepository nodeRepository;

    private final String FINAL_TREE_ID = "nary_tree";

    @Override
    public void addNode(NodeDto nodeDto) {
        Node dummyRootNode = getDummyHeadNode();

        String newNodeId = UUID.randomUUID().toString();

        Node newNode = Node.builder()
                .id(newNodeId)
                .path(nodeDto.getPath())
                .icon(nodeDto.getIcon())
                .title(nodeDto.getTitle())
                .children(new ArrayList<>())
                .build();

        Node root = naryTree.addNewNode(newNode, dummyRootNode,nodeDto.getParentId());

        saveTreeToDatabase(root);
        saveNodeData(newNode);
    }

    @Override
    public void updateNode(NodeUpdateDto menuUpdateDto) {
        Node dummyRootNode = getDummyHeadNode();
        Node root = naryTree.updateExistingNode(menuUpdateDto,dummyRootNode);

        saveTreeToDatabase(root);
        Node node = Node.builder()
                .id(menuUpdateDto.getId())
                .title(menuUpdateDto.getTitle())
                .path(menuUpdateDto.getPath())
                .icon(menuUpdateDto.getIcon())
                .build();

        saveNodeData(node);

    }

    @Override
    public List<Node> getAllNodes() {
        Node dummyHeadNode = getDummyHeadNode();
        return dummyHeadNode.getChildren();
    }
    @Override
    public List<NodePath> getAllNameId() {

        return nodePathRepository.findAll();

    }

    @Override
    public void deleteNode(String nodeId) {
        Node root = getDummyHeadNode();

        List<Node> result = naryTree.deleteNode(root,nodeId);
        //result[0] => root ,,,,result[1] => deletedNode=====>>> we have to delete these nodes from NodePath repository...

        root = result.get(0);

        saveTreeToDatabase(root);

        Node deletedNode = result.get(1);
        // TODO:======================**********======
        // have to write function to delete all node from node path repo....

        deleteNodesFromNodePathRepository(deletedNode);

    }

    //===================================================================================

    // *************************** delete nodes details from node path repo *********************
    private void deleteNodesFromNodePathRepository(Node deletedNode) {
        List<String> listOfDeletedNodesId = new ArrayList<>();

        createListOfDeletedId(deletedNode,listOfDeletedNodesId);

        nodePathRepository.deleteAllById(listOfDeletedNodesId);
    }
    private void createListOfDeletedId(Node root, List<String> listOfDeletedNodeId) {

        listOfDeletedNodeId.add(root.getId());
        List<Node> nodeList = root.getChildren();

        for(Node node : nodeList) {
            createListOfDeletedId(node,listOfDeletedNodeId);
        }
    }

    //*****************************************************************************************************
    private Node getDummyHeadNode() {
        return nodeRepository.findById(FINAL_TREE_ID)
                .orElseGet(this::createDummyNodeAndSaveToDb);

    }

    private Node saveTreeToDatabase(Node dummyHeadNode) {
        dummyHeadNode.setId(FINAL_TREE_ID);
        return nodeRepository.save(dummyHeadNode);
    }

    //================================================
    // this will only run for first time to create dummy root node....
    private Node createDummyNodeAndSaveToDb() {
        Node node = Node.builder()
                .title("dummyHeadNode")
                .children(new ArrayList<>())
                .build();

        return saveTreeToDatabase(node);
    }

    //=======================================================

    private void saveNodeData( Node node) {
//        node.setChildren(null);
        NodePath data = NodePath.builder()
                .id(node.getId())
                .path(node.getPath())
                .title(node.getTitle())
                .icon(node.getIcon())
                .build();
        nodePathRepository.save(data);
    }

}
