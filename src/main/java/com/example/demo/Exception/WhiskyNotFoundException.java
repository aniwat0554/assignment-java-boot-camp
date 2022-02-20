package com.example.demo.Exception;

public class WhiskyNotFoundException extends RuntimeException {
    public WhiskyNotFoundException(String name) {
        super(name);
    }
}
