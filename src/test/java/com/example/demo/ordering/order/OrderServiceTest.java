package com.example.demo.ordering.order;

import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.ordering.basket.BasketService;
import com.example.demo.ordering.objects.BankPayment;
import com.example.demo.ordering.objects.UsersBasket;
import com.example.demo.ordering.objects.UsersOrder;
import com.example.demo.ordering.objects.WhiskyOrder;
import com.example.demo.users.UsersService;
import com.example.demo.users.objects.User;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {


    @Mock
    private OrderRepository orderRepository;


    @Mock
    private BasketService basketService;


    @Test
    void getUsersOrder() {
        UsersOrder usersOrder = new UsersOrder();
        usersOrder.setId(1);
        User user = new User();
        user.setUsername("CorrectName");
        usersOrder.setShopper(user);
        when(orderRepository.findById(1)).thenReturn(Optional.of(usersOrder));
        String paramName = "CorrectName";

        OrderService orderService = new OrderService(orderRepository);
        UsersOrder checkUserOrder = orderService.getUsersOrder(1,paramName);
        assertEquals("CorrectName",checkUserOrder.getShopper().getUsername());

        String result = "Default";
        try{
            UsersOrder checkWrongUserOrder = orderService.getUsersOrder(1,"Wrong");
        }catch(OrderNotFoundException ex){
            result = "Changed";
        }
        assertEquals("Changed",result);
    }
}