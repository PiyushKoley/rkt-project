package com.rkt.app.mongo.repository;

import com.rkt.app.mongo.entity.NodePath;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NodePathRepository extends MongoRepository<NodePath,String> {
}
