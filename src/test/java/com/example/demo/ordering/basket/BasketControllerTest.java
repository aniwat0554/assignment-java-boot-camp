package com.example.demo.ordering.basket;

import com.example.demo.ordering.objects.BasketPutResponse;
import com.example.demo.ordering.objects.BasketResponse;
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
    @Order(2)
    @DisplayName("Get Aniwat's basket which already contains 1 item")
    void getBasket() {
        BasketResponse response = testRestTemplate.getForObject("/ordering/basket/Aniwat", BasketResponse.class);
        assertEquals(1,response.getWhiskyList().size());
        assertEquals("BlackLabel Johny Walker",response.getWhiskyList().get(0).getName());
    }

    @Test
    @Order(1)
    @DisplayName("Put whisky into Aniwat's basket which should now contains 2 item")
    void putInBasket() {
        BasketPutResponse putItemResponse = testRestTemplate.postForObject("/ordering/basket/Aniwat/whisky", 3, BasketPutResponse.class);
        BasketResponse getResponse = testRestTemplate.getForObject("/ordering/basket/Aniwat", BasketResponse.class);
        assertEquals(1,getResponse.getWhiskyList().size());

        assertEquals(false,getResponse.getWhiskyList().stream().filter(whisky -> "BlackLabel Johny Walker".equals(whisky.getName())).findAny().isEmpty());

    }
}