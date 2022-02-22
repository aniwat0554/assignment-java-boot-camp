package com.example.demo.ordering.order;

import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.users.objects.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderingControllerAdvice {
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public OrderAddressUpdateResponse orderNotFound(OrderNotFoundException e){
        OrderAddressUpdateResponse errorResponse =  new OrderAddressUpdateResponse();
        errorResponse.setStatusMessage(e.getMessage());
        return errorResponse;
    }
}

