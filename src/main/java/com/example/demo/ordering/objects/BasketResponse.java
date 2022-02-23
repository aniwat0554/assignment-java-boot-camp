package com.example.demo.ordering.objects;

import com.example.demo.whiskies.objects.Whisky;

import java.util.List;

public class BasketResponse {
    private List<Whisky> whiskyList;

    private int totalPrice;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BasketResponse() {
    }

    public List<Whisky> getWhiskyList() {
        return whiskyList;
    }

    public void setWhiskyList(List<Whisky> whiskyList) {
        this.whiskyList = whiskyList;
    }

    public BasketResponse(List<Whisky> whiskyList) {
        this.whiskyList = whiskyList;
    }
}
