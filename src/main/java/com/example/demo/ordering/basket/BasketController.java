package com.example.demo.ordering.basket;

import com.example.demo.ordering.objects.Basket;
import com.example.demo.ordering.objects.BasketPutResponse;
import com.example.demo.ordering.objects.BasketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/{name}")
    public BasketResponse getBasket(@PathVariable String name){
        Basket basket = basketService.getUsersBasket(name).getBasket();
        return new BasketResponse(basket);
    }

    @PostMapping("/{name}/whisky")
    public BasketPutResponse putInBasket(@PathVariable String name, @RequestBody int whiskyId){
        this.basketService.putInBasket(name,whiskyId);
        return new BasketPutResponse();
    }

}
