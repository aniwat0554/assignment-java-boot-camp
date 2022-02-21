package com.example.demo.ordering.order;

import com.example.demo.ordering.objects.Order;
import com.example.demo.users.objects.User;

public class UsersOrder {
    private Order order;

    public UsersOrder(Order order, User shopper) {
        this.order = order;
        this.shopper = shopper;
    }

    public UsersOrder() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getShopper() {
        return shopper;
    }

    public void setShopper(User shopper) {
        this.shopper = shopper;
    }

    private User shopper;
}
