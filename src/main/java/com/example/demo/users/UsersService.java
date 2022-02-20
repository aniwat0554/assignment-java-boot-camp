package com.example.demo.users;

import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.shipment.Address;
import com.example.demo.users.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;
    public User getUser(String name){
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isPresent()){
            return user.get();
        }


        throw new UserNotFoundException(name);
    }
}
