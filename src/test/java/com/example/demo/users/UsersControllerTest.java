package com.example.demo.users;

import com.example.demo.users.objects.UserListResponse;
import com.example.demo.users.objects.UserResponse;
import com.example.demo.whiskies.objects.WhiskiesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Get user : get user by name expect 1 user with matching name")
    void getUser() {
        UserResponse response = testRestTemplate.getForObject("/users/Aniwat",UserResponse.class);
        assertEquals("21000",response.getUser().getAddress().getPostcode());
        assertEquals("Aniwat",response.getUser().getUsername());

    }

    @Test
    @DisplayName("Get all users : to make sure that data was initialized correctly in H2")
    void getAllUsers(){
        UserListResponse response = testRestTemplate.getForObject("/users",UserListResponse.class);
        assertEquals(1,response.getUserList().size());
    }


}