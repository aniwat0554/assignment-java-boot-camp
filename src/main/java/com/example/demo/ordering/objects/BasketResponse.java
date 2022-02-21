package com.example.demo.ordering.objects;

public class BasketResponse {
    private Basket basket;

    public BasketResponse(Basket basket) {
        this.basket = basket;
    }

    public BasketResponse() {
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
