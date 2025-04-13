package com.hritech.hinotes.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hritech.hinotes.Model.User;



public interface UserRepo extends MongoRepository<User, String >{
    Optional<User> findByName(String name);
}
