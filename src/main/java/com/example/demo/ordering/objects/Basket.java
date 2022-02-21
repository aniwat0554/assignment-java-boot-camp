package com.example.demo.ordering.objects;

import com.example.demo.users.objects.User;
import com.example.demo.whiskies.objects.Whisky;

import javax.persistence.*;
import java.util.List;

@Entity
public class Basket {

    @ManyToMany
    private List<Whisky> whiskies;
    @Id
    @GeneratedValue
    private int id;

    public Basket(List<Whisky> whiskies) {
        this.whiskies = whiskies;
    }

    public Basket() {
    }

    public List<Whisky> getWhiskies() {
        return whiskies;
    }

    public void setWhiskies(List<Whisky> whiskies) {
        this.whiskies = whiskies;
    }
}
