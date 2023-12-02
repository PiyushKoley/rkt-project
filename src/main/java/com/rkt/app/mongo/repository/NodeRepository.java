package com.rkt.app.mongo.repository;

import com.rkt.app.mongo.entity.Node;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NodeRepository extends MongoRepository<Node,String> {
}
