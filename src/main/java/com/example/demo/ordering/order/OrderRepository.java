package com.example.demo.ordering.order;

import com.example.demo.ordering.objects.UsersBasket;
import com.example.demo.ordering.objects.UsersOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<UsersOrder,Integer> {
    Optional<UsersOrder> findByWhiskyOrder_CreditCardPayment_transactionId(String transactionId);
}
