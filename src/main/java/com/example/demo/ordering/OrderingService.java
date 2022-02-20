package com.example.demo.ordering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderingService {

    @Autowired
    private UsersBasketRepository usersBasketRepository;

    public Basket getBasket(String name){
        Basket basket = this.usersBasketRepository.findByBasketOwner_username(name).get().getBasket();
        return basket;
    }
}
