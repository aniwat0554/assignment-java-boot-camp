package com.example.demo.ordering.objects;

import com.example.demo.users.objects.User;
import com.example.demo.whiskies.objects.Whisky;

import javax.persistence.*;
import java.util.List;

@Entity
public class UsersBasket {
    @Id
    @GeneratedValue
    private int id;

    public UsersBasket() {
    }

    public UsersBasket( User basketOwner, List<Whisky> whiskyInBasket) {
        this.basketOwner = basketOwner;
        this.whiskyInBasket = whiskyInBasket;
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

    @OneToOne
    private User basketOwner;

    public List<Whisky> getWhiskyInBasket() {
        return whiskyInBasket;
    }

    public void setWhiskyInBasket(List<Whisky> whiskyInBasket) {
        this.whiskyInBasket = whiskyInBasket;
    }

    @ManyToMany
    private List<Whisky> whiskyInBasket;
}
