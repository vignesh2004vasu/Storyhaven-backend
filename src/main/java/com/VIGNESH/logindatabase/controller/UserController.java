package com.VIGNESH.logindatabase.controller;

import com.VIGNESH.logindatabase.exception.UserNotFoundException;
import com.VIGNESH.logindatabase.model.User;
import com.VIGNESH.logindatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
// @CrossOrigin("https://storyhaven.netlify.app/")
// @CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){

        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable String id){
        return userRepository.findById(id)

                .orElseThrow(()->new UserNotFoundException(id));

    }



    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable String id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable String id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "user with id "+id+" has been deleted success.";
    }
}
