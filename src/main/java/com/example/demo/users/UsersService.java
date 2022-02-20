package com.example.demo.users;

import com.example.demo.shipment.Address;
import com.example.demo.users.objects.User;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    public User getUser(String name){
        Address address = new Address("Rayong","Noenphra","Rayong City","21000","xxx");
        User user = new User(address,"Aniwat");
        return user;
    }
}
