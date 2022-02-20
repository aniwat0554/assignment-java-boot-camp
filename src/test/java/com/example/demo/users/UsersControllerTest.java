package com.example.demo.users;

import com.example.demo.users.objects.UserResponse;
import com.example.demo.whiskies.objects.WhiskiesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Get user : get user by name expect 1 user with matching name")
    void getUser() {
        UserResponse response = testRestTemplate.getForObject("/users/aniwat",UserResponse.class);
        assertEquals("21000",response.getUser().getAddress().getPostcode());
        assertEquals("Aniwat",response.getUser().getUsername());

    }


}