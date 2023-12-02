package com.rkt.app.mongo.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "final_tree")
public class Node {

    @Id
    private String id; // have to
    private String title;
    private String path;
    private String icon;
//    private int presentInParentIndex;

    private List<Node> children;
}
