package com.example.demo.ordering.order;

import com.example.demo.ordering.basket.BasketService;
import com.example.demo.ordering.objects.CheckoutResponse;
import com.example.demo.ordering.objects.UsersOrder;
import com.example.demo.ordering.objects.UsersOrderDebug;
import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;

@RestController
public class OrderingController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BasketService basketService;
    @PostMapping("/ordering/checkout")
    public CheckoutResponse basketCheckOut(@RequestBody String shopperName){
        //Implement Transaction here
        int usersOrderId = orderService.createNewOrder(shopperName);
        basketService.clearBasket(shopperName);

        CheckoutResponse checkoutResponse = new CheckoutResponse();
        checkoutResponse.setCreatedOrderId(usersOrderId);
        return checkoutResponse;
    }


    @GetMapping("/ordering/order/{id}")
    public UsersOrder getOrder(@PathVariable int id){
        return orderService.getUsersOrder(id);
    }

    //Created for troubleshooting To Be Removed
    @GetMapping("/ordering/order")
    public OrderListResponse getAnyOrder(){
        OrderListResponse orderListResponse = new OrderListResponse();
        orderListResponse.setUsersOrderList(orderService.getAllOrder());
        return orderListResponse;
    }
}
