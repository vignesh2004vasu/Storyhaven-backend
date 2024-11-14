package com.VIGNESH.logindatabase.controller;

import com.VIGNESH.logindatabase.model.User;
import com.VIGNESH.logindatabase.model.UserInfo;
import com.VIGNESH.logindatabase.repository.UserInfoRepository;
import com.VIGNESH.logindatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
// @CrossOrigin("https://storyhaven.netlify.app/")
// @CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRepository userRepository;



    @PostMapping("/adddetails/{userId}")
    public ResponseEntity<UserInfo> addDetailsToUser(@PathVariable("userId") String userId, @RequestBody UserInfo userInfo) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userInfo.setUser(user);
            UserInfo savedUserInfo = userInfoRepository.save(userInfo);
            user.setUserInfo(savedUserInfo); // Set the saved UserInfo to the User
            userRepository.save(user); // Update the User entity with the UserInfo
            return new ResponseEntity<>(savedUserInfo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/get/{userId}")
    public ResponseEntity<UserInfo> getUserInfoByUserId(@PathVariable("userId") String userId) {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findByUserId(userId);
        return userInfoOptional.map(userInfo -> new ResponseEntity<>(userInfo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<UserInfo>> getAllUserInfo() {
        List<UserInfo> userInfos = userInfoRepository.findAll();
        return new ResponseEntity<>(userInfos, HttpStatus.OK);
    }

    @GetMapping("/sort/{city}")
    public ResponseEntity<Page<Object[]>> sortUsersAndInfoByCity(@PathVariable("city") String city) {
        // Set default values for page and size
        int page = 0;
        int size = 10;

        Page<Object[]> usersAndInfoByCity = userInfoRepository.findUsersAndInfoByCity(city, PageRequest.of(page, size));
        return new ResponseEntity<>(usersAndInfoByCity, HttpStatus.OK);
    }
}
