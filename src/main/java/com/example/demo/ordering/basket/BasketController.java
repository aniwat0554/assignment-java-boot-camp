package com.example.demo.ordering.basket;

import com.example.demo.ordering.objects.BasketPutResponse;
import com.example.demo.ordering.objects.BasketResponse;
import com.example.demo.whiskies.objects.Whisky;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordering/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/{name}")
    public BasketResponse getBasket(@PathVariable String name){
        List<Whisky> whiskyList = basketService.getUsersBasket(name).getWhiskyInBasket();
        return new BasketResponse(whiskyList);
    }

    @PostMapping("/{name}/whisky")
    public BasketPutResponse putInBasket(@PathVariable String name, @RequestBody int whiskyId){
        this.basketService.putInBasket(name,whiskyId);
        return new BasketPutResponse();
    }

}
