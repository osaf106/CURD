package com.learning.curd.Service;

import com.learning.curd.Entity.CurdEntity;
import com.learning.curd.Repository.CurdReposotroyMongoDB;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CurdService {


    @Autowired
    private CurdReposotroyMongoDB curdReposotroyMongoDB;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    // save into Mongo
    public CurdEntity createEntity(CurdEntity curdEntity)
    {
//        curdEntity.setPassword(passwordEncoder.encode(curdEntity.getPassword()));
        return curdReposotroyMongoDB.save(curdEntity);
    }
    // get from Mongo
    public List<CurdEntity> getAllEntities()
    {
        return curdReposotroyMongoDB.findAll();
    }
    // get user by ID
    public Optional<CurdEntity> getById(String id)
    {
        return curdReposotroyMongoDB.findById(id);
    }
    // delete user by id
    public void deleteById(String id)
    {
        curdReposotroyMongoDB.deleteById(id);
    }
    public Optional<CurdEntity> findUserByEmail(String email) {
        return curdReposotroyMongoDB.findByEmail(email);
    }

}
