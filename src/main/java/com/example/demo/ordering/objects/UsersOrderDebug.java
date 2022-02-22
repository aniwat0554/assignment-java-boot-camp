package com.example.demo.ordering.objects;


import com.example.demo.users.objects.User;

import javax.persistence.*;

@Entity
public class UsersOrderDebug {
    @Id
    @GeneratedValue
    private int id;

    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public UsersOrderDebug() {
    }

}
