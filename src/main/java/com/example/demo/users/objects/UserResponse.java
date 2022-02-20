package com.example.demo.users.objects;

public class UserResponse {
    private User user;

    public UserResponse() {
    }

    public UserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
