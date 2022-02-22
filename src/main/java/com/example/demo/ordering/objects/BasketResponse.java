package com.example.demo.ordering.objects;

import com.example.demo.whiskies.objects.Whisky;

import java.util.List;

public class BasketResponse {
    private List<Whisky> whiskyList;

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
