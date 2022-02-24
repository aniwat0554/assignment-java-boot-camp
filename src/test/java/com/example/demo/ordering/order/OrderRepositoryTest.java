package com.example.demo.ordering.order;

import com.example.demo.ordering.objects.CreditCardPayment;
import com.example.demo.ordering.objects.PurchasedWhisky;
import com.example.demo.ordering.objects.UsersOrder;
import com.example.demo.ordering.objects.WhiskyOrder;
import com.example.demo.pricing.Price;
import com.example.demo.users.UserRepository;
import com.example.demo.users.objects.User;
import com.example.demo.whiskies.WhiskyRepository;
import com.example.demo.whiskies.objects.Whisky;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void findByName() {
        //Act
        UsersOrder usersOrder = new UsersOrder();
        WhiskyOrder order = new WhiskyOrder();
        CreditCardPayment payment = new CreditCardPayment();
        payment.setTransactionId("4321");
        order.setCreditCardPayment(payment);
        List<PurchasedWhisky> whiskyList = new ArrayList<PurchasedWhisky>();
        PurchasedWhisky whisky = new PurchasedWhisky();
        Price price = new Price();
        price.setDiscount(0);
        price.setBasePrice(1);
        whisky.setPrice(price);
        whisky.setName("Test");
        whiskyList.add(whisky);
        order.setTotalPrice(1);
        order.setWhiskyList(whiskyList);
        usersOrder.setWhiskyOrder(order);
        orderRepository.save(usersOrder);
        UsersOrder verifyingOrder = orderRepository.findByWhiskyOrder_CreditCardPayment_transactionId("4321").get();
        //Assert
        assertEquals("Test",verifyingOrder.getWhiskyOrder().getWhiskyList().get(0).getName());

        assertEquals(1,verifyingOrder.getWhiskyOrder().getTotalPrice());

        assertEquals(1,verifyingOrder.getWhiskyOrder().getWhiskyList().get(0).getPrice().getNetPrice());
    }
}