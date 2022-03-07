package com.example.demo.users;

import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.shipment.Address;
import com.example.demo.users.objects.Role;
import com.example.demo.users.objects.User;
import com.example.demo.whiskies.objects.Whisky;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(String name){
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isPresent()){
            return user.get();
        }


        throw new UserNotFoundException(name);
    }

    public User createUser(String username, String password, Address address, Set<Role> roles){
        User user = new User(address,username,this.passwordEncoder.encode(password));
        user.setAuthorities(roles);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
