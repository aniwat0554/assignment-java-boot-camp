package com.example.demo.users;

import com.example.demo.shipment.Address;
import com.example.demo.users.objects.User;
import com.example.demo.users.objects.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @GetMapping("/users/{name}")
    public UserResponse getUser(@PathVariable String name){
        Address address = new Address("Rayong","Noenphra","Rayong City","21000","xxx");
        User user = new User(address,"Aniwat");
        return new UserResponse(user);
    }
}
