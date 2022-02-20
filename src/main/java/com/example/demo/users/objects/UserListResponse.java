package com.example.demo.users.objects;

import java.util.ArrayList;
import java.util.List;

public class UserListResponse {
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public UserListResponse() {
    }
}
