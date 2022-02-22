package com.example.demo.ordering.order;

import com.example.demo.ordering.objects.BasketResponse;
import com.example.demo.ordering.objects.CheckoutResponse;
import com.example.demo.ordering.objects.UsersOrder;
import com.example.demo.ordering.objects.UsersOrderDebug;
import com.example.demo.users.UsersService;
import com.example.demo.users.objects.UserResponse;
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

        UsersOrder responseItem = testRestTemplate.getForObject("/ordering/order/"+response.getCreatedOrderId(), UsersOrder.class);
        assertEquals(response.getCreatedOrderId(),responseItem.getId());
        assertEquals("RedLabel Johny Walker",responseItem.getWhiskyOrder().getWhiskyList().get(0).getName());

    }

    @Test
    @Order(2)
    @DisplayName("Update address and check if it is indeed updated")
    void updateAddress(){

        CheckoutResponse checkOutResponse = testRestTemplate.postForObject("/ordering/checkout","Aniwat", CheckoutResponse.class);

        UserResponse userResponse = testRestTemplate.getForObject("/users/Aniwat",UserResponse.class);
        OrderAddressUpdateResponse orderAddressResponse = testRestTemplate.postForObject("/ordering/order/"+checkOutResponse.getCreatedOrderId()+"/address",userResponse.getUser().getAddress(),OrderAddressUpdateResponse.class);

        UsersOrder responseItem = testRestTemplate.getForObject("/ordering/order/"+checkOutResponse.getCreatedOrderId(), UsersOrder.class);
        assertEquals("21000",responseItem.getWhiskyOrder().getAddress().getPostcode());
    }


}