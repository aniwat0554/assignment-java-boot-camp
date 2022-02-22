package com.example.demo.ordering.order;

import com.example.demo.ordering.objects.UsersOrder;

import java.util.List;

public class OrderListResponse {
    private List<UsersOrder> usersOrderList;

    public List<UsersOrder> getUsersOrderList() {
        return usersOrderList;
    }

    public void setUsersOrderList(List<UsersOrder> usersOrderList) {
        this.usersOrderList = usersOrderList;
    }

    public OrderListResponse() {
    }
}
