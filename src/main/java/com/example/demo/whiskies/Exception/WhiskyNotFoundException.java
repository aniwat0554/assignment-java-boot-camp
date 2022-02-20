package com.example.demo.whiskies.Exception;

public class WhiskyNotFoundException extends RuntimeException {
    public WhiskyNotFoundException(String name) {
        super(name);
    }
}
