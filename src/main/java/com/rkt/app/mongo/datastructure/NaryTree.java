package com.rkt.app.mongo.datastructure;

import com.rkt.app.mongo.dto.NodeUpdateDto;
import com.rkt.app.mongo.entity.Node;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NaryTree {

    public Node addNewNode(Node newNode, Node root,String parentId) {

        Node parentNode = searchNodeOrElseThrowException(root,parentId);

        List<Node> children = parentNode.getChildren();
        children.add(newNode);

        children.sort((node1,node2)-> node1.getTitle().compareToIgnoreCase(node2.getTitle()));

        return root;
    }

    public Node updateExistingNode(NodeUpdateDto nodeUpdateDto, Node root) {
        List<Node> nodeWithParent = searchNodeWithParentOrElseThrowException(root,nodeUpdateDto.getId());
        if(nodeWithParent.size()==1) nodeWithParent.add(root);

        Node node = nodeWithParent.get(0);
        node.setTitle(nodeUpdateDto.getTitle());
        node.setPath(nodeUpdateDto.getPath());
        node.setIcon(nodeUpdateDto.getIcon());

        Node parentNode = nodeWithParent.get(1);
        // sorting again with updated values...
        parentNode.getChildren().sort((node1, node2) -> node1.getTitle().compareToIgnoreCase(node2.getTitle()));

        return root;
    }

    public List<Node> deleteNode(Node root, String nodeId) {

        List<Node> nodeWithParent = searchNodeWithParentOrElseThrowException(root, nodeId);

        Node nodeToDelete = nodeWithParent.get(0);
        Node parentNode = nodeWithParent.get(1);

        parentNode.getChildren().remove(nodeToDelete);

        return List.of(root,nodeToDelete);
    }



    //=========================================================================================
    private Node searchNodeOrElseThrowException(Node root, String nodeId) {
        Node node = searchNode(root,nodeId);
        if(node == null) {
            throw new NodeNotFoundException(
                    String.format("Menu with parent id: %s is not present",nodeId)
            );
        }

        return node;
    }


    // searching node with nodeId in tree.....
    private Node searchNode(Node root, String nodeId) {
        if(nodeId==null) {
            return root;
        }

        Node result = null;

        List<Node> children = root.getChildren();

        for(Node node : children) {
            if(node.getId().equalsIgnoreCase(nodeId)) {
                return node;
            }

            // recursion to find the node...
            result = searchNode(node,nodeId);

            if(result != null){
                break;
            }
        }

        return result;
    }

    //=============================================================================

    private List<Node> searchNodeWithParentOrElseThrowException(Node root, String nodeId) {
        List<Node> nodeWithParent = searchNodeWithParent(root,nodeId, new ArrayList<>());
        if(nodeWithParent == null ||nodeWithParent.size() == 0) {
            throw new NodeNotFoundException(
                    String.format("Menu with id: %s is not present",nodeId)
            );
        }

        return nodeWithParent;
    }

    private List<Node> searchNodeWithParent(Node root, String nodeId,List<Node> store) {
        if(nodeId==null) {
            return store;
        }

        List<Node> result = null;

        List<Node> children = root.getChildren();

        for(Node node : children) {
            if(node.getId().equalsIgnoreCase(nodeId)) {
                store.add(node);//this is the main node which we want to update...
                return store;
            }

            // recursion to find the node...
            result = searchNodeWithParent(node,nodeId,store);

            if(result != null){

                // this will only store node and parent ...
                //otherwise it will add all nodes from node to root
                if(result.size() == 1) {
                    result.add(node); // this is the parent node...
                }
                break;
            }
        }

        return result;
    }
}
