package com.example.demo.users;

import com.example.demo.users.objects.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerFailingTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Get user : get user by a wrong name expect error")
    void getUser() {
        ResponseEntity<UserResponse> response = testRestTemplate.getForEntity("/users/aniwatsss",UserResponse.class);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }


}