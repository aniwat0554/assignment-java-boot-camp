package com.example.demo.ordering.order;

import com.example.demo.ordering.basket.BasketService;
import com.example.demo.ordering.objects.UsersOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private BasketService basketService;
    @Mock
    private OrderRepository orderRepository;

    @Test
    void createNewOrder() {
    }
}