package com.example.demo.ordering.order;

import com.example.demo.ordering.objects.BasketResponse;
import com.example.demo.ordering.objects.CheckoutResponse;
import com.example.demo.ordering.objects.UsersOrder;
import com.example.demo.ordering.objects.UsersOrderDebug;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private OrderService orderService;



    @Test
    @Order(1)
    @DisplayName("Check out to create order which must contain whiskies list")
    void basketCheckOut() {
        CheckoutResponse response = testRestTemplate.postForObject("/ordering/checkout","Aniwat", CheckoutResponse.class);
        //UsersOrderDebug usersOrder = orderService.getUsersOrder(response.getCreatedOrderId());
        //int id = usersOrder.getId();
        //assertEquals("RedLabel Johny Walker",usersOrder);

        UsersOrder responseItem = testRestTemplate.getForObject("/ordering/order/"+response.getCreatedOrderId(), UsersOrder.class);
        assertEquals(response.getCreatedOrderId(),responseItem.getId());
    }

}