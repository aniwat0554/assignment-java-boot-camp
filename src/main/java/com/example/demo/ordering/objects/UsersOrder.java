package com.example.demo.ordering.objects;

import com.example.demo.users.objects.User;

import javax.persistence.*;

@Entity
public class UsersOrder {

    @Id
    @GeneratedValue
    private int id;
    

    @OneToOne(optional = false,cascade= CascadeType.ALL)
    private WhiskyOrder whiskyOrder;

    public WhiskyOrder getWhiskyOrder() {
        return whiskyOrder;
    }

    public void setWhiskyOrder(WhiskyOrder whiskyOrder) {
        this.whiskyOrder = whiskyOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsersOrder(WhiskyOrder whiskyOrder, User shopper) {
        this.whiskyOrder = whiskyOrder;
        this.shopper = shopper;
    }

    public UsersOrder() {
    }


    public User getShopper() {
        return shopper;
    }

    public void setShopper(User shopper) {
        this.shopper = shopper;
    }

    @OneToOne
    private User shopper;
}
