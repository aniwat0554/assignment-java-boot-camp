package com.example.demo.users;

import com.example.demo.users.objects.User;
import com.example.demo.users.objects.UserListResponse;
import com.example.demo.users.objects.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {


    @Autowired
    private UsersService usersService;

    @GetMapping("/users/{name}")
    public UserResponse getUser(@PathVariable String name, Authentication authentication){
        User user = new User();
        if(name.equals(authentication.getName())){
            user = usersService.getUser(name);
        }

        return new UserResponse(user);
    }

    //This method exist for the purpose of confirming correctness of data modeling with JPA
    @GetMapping("/users")
    public UserListResponse getUsers(){
        List<User> users = usersService.getAllUsers();
        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setUserList(users);
        return userListResponse;
    }
}
