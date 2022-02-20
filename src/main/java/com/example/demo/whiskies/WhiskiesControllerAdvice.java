package com.example.demo.whiskies;

import com.example.demo.whiskies.Exception.WhiskyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WhiskiesControllerAdvice {

    @ExceptionHandler(WhiskyNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public WhiskyDetailResponse whiskyNotFound(WhiskyNotFoundException e){
        WhiskyDetailResponse errorResponse =  new WhiskyDetailResponse();
        errorResponse.setErrorMessage(e.getMessage());
        return errorResponse;
    }
}
