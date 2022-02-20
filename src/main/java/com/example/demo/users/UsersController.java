package com.example.demo.users;

import com.example.demo.shipment.Address;
import com.example.demo.users.objects.User;
import com.example.demo.users.objects.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {


    @Autowired
    private UsersService usersService;

    @GetMapping("/users/{name}")
    public UserResponse getUser(@PathVariable String name){
        User user = usersService.getUser("aniwat");
        return new UserResponse(user);
    }
}
