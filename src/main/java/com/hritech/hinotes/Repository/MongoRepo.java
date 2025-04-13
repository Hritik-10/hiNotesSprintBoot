package com.hritech.hinotes.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.hritech.hinotes.Model.Notes;


public interface MongoRepo extends MongoRepository<Notes, String>{
    //Eat five star, Do nothing !!
}
