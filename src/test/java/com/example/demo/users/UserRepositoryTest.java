package com.example.demo.users;

import com.example.demo.users.objects.User;
import com.example.demo.whiskies.WhiskyRepository;
import com.example.demo.whiskies.objects.Whisky;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByName() {
        //Act
        User aniwat = userRepository.findByUsername("Aniwat").get();
        //Assert
        assertEquals("Aniwat",aniwat.getUsername());
    }
}