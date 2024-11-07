package com.learning.curd.Repository;

import com.learning.curd.Entity.CurdEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CurdReposotroyMongoDB extends MongoRepository<CurdEntity, String> {
    Optional<CurdEntity> findByEmail(String email);
}
