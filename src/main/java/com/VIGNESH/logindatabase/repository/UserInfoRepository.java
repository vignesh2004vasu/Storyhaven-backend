package com.VIGNESH.logindatabase.repository;

import com.VIGNESH.logindatabase.model.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserInfoRepository extends MongoRepository<UserInfo, String> { // Use String for ID

    Page<Object[]> findUsersAndInfoByCity(String city, Pageable pageable); // Adjust query methods as needed

    Optional<UserInfo> findByUserId(String userId);
}
