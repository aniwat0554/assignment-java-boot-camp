package com.example.demo.ordering.objects;

import com.example.demo.users.objects.User;
import com.example.demo.whiskies.objects.Whisky;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class UsersBasket {
    @Id
    @GeneratedValue
    private int id;

    @Transient
    private int totalPrice;

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

    public int getTotalPrice(){
        Optional<Integer> totalPrice = this.whiskyInBasket.stream().map((whisky) -> whisky.getPrice().getNetPrice()).reduce((total, num) -> total+num);
        if(totalPrice.isPresent()){
            return totalPrice.get();
        }

        return 0;
    }
}
