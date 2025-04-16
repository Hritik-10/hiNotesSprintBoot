package com.hritech.hinotes.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.hritech.hinotes.Model.Notes;
import java.util.List;



public interface MongoRepo extends MongoRepository<Notes, String>{
    //Eat five star, Do nothing !!

    List<Notes> findByUser(String user);
}
