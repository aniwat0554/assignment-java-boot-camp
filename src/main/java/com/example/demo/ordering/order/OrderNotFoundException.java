package com.example.demo.ordering.order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(int id) {
        super(String.valueOf(id));
    }
}
