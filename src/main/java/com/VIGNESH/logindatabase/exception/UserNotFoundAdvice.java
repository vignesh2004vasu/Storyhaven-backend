package com.VIGNESH.logindatabase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UserNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
        public Map<String,String> exceptionHandler(UserNotFoundException exception){
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("error Message",exception.getMessage());

        return errorMap;
    }


}
