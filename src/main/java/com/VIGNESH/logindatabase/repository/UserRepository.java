package com.VIGNESH.logindatabase.repository;

import com.VIGNESH.logindatabase.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> { // Use String for ID

}
