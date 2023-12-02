package com.rkt.app.mongo.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "every_node_path_map")
@Builder
public class NodePath {

    @Id
    private String id; // have to hard code this value otherwise it will generate a random string....

    private Map<String, List<?>> mapOfEveryNodePath;

}
