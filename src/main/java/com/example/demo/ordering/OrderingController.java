package com.example.demo.ordering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderingController {

    @Autowired
    private OrderingService basketService;

    @GetMapping("/basket/{name}")
    public BasketResponse getBasket(@PathVariable String name){
        Basket basket = basketService.getBasket(name);
        return new BasketResponse(basket);
    }
}
