package com.VIGNESH.logindatabase.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id){
        super("Could not find the user with id"+id);
    }
}
