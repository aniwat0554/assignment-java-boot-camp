package com.example.demo.ordering.objects;

import com.example.demo.users.objects.User;

import javax.persistence.*;

@Entity
public class UsersBasket {
    @Id
    @GeneratedValue
    private int id;

    public UsersBasket() {
    }

    public UsersBasket( User basketOwner, Basket basket) {
        this.basketOwner = basketOwner;
        this.basket = basket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getBasketOwner() {
        return basketOwner;
    }

    public void setBasketOwner(User basketOwner) {
        this.basketOwner = basketOwner;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @OneToOne
    private User basketOwner;

    @OneToOne(optional = false,cascade= CascadeType.ALL)
    private Basket basket;
}
