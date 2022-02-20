package com.example.demo.users;

import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.users.objects.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsersControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserResponse whiskyNotFound(UserNotFoundException e){
        UserResponse errorResponse =  new UserResponse();
        errorResponse.setErrorMessage(e.getMessage());
        return errorResponse;
    }

}
