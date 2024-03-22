package com.example.loginDemo.Repo;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.loginDemo.Model.userData;


@Repository
public interface userRepo extends MongoRepository<userData,ObjectId>{

    userData findByUserName(String userName);

    @Query(value = "{}", fields = "{'userName':1, 'email':1 , '_id':0}")
    List<userData> findAllUsers();
}
