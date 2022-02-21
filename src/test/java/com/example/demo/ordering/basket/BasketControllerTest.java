package com.example.demo.ordering.basket;

import com.example.demo.ordering.objects.BasketPutResponse;
import com.example.demo.ordering.objects.BasketResponse;
import com.example.demo.users.objects.UserResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BasketControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    @Order(1)
    @DisplayName("Get Aniwat's basket which already contains 1 item")
    void getBasket() {
        BasketResponse response = testRestTemplate.getForObject("/basket/Aniwat", BasketResponse.class);
        assertEquals(1,response.getBasket().getWhiskies().size());
        assertEquals("RedLabel Johny Walker",response.getBasket().getWhiskies().get(0).getName());
    }

    @Test
    @Order(2)
    @DisplayName("Put whisky into Aniwat's basket which should now contains 2 item")
    void putInBasket() {
        BasketPutResponse putItemResponse = testRestTemplate.postForObject("/basket/Aniwat/whisky", 3, BasketPutResponse.class);
        BasketResponse getResponse = testRestTemplate.getForObject("/basket/Aniwat", BasketResponse.class);
        assertEquals(2,getResponse.getBasket().getWhiskies().size());
        assertEquals(false,getResponse.getBasket().getWhiskies().stream().filter(whisky -> "RedLabel Johny Walker".equals(whisky.getName())).findAny().isEmpty());
        assertEquals(false,getResponse.getBasket().getWhiskies().stream().filter(whisky -> "BlackLabel Johny Walker".equals(whisky.getName())).findAny().isEmpty());

    }
}