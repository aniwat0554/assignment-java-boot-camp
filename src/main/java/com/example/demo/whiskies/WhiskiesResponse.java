package com.example.demo.whiskies;

public class WhiskiesResponse {
    private String name;
    private int degree;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    private final int USUAL_DEGREE = 40;
    public WhiskiesResponse(String name){
        this.name = name;
        this.degree = USUAL_DEGREE;
    }

    public WhiskiesResponse(String name,int degree){
        this.name = name;
        this.degree = degree;
    }
}
