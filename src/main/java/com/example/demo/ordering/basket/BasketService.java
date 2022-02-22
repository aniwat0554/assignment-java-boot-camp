package com.example.demo.ordering.basket;

import com.example.demo.ordering.objects.UsersBasket;
import com.example.demo.whiskies.WhiskyRepository;
import com.example.demo.whiskies.objects.Whisky;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    @Autowired
    private UsersBasketRepository usersBasketRepository;

    @Autowired
    private WhiskyRepository whiskyRepository;

    public UsersBasket getUsersBasket(String name){
        UsersBasket usersBasket = this.usersBasketRepository.findByBasketOwner_username(name).get();
        return usersBasket;
    }

    public void putInBasket(String name,int id){
        UsersBasket usersBasket = this.getUsersBasket(name);
        Whisky whisky = this.whiskyRepository.getById(id);
        usersBasket.getWhiskyInBasket().add(whisky);
        this.usersBasketRepository.save(usersBasket);
    }
}
