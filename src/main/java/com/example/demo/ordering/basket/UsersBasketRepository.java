package com.example.demo.ordering.basket;

import com.example.demo.ordering.objects.UsersBasket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersBasketRepository extends JpaRepository<UsersBasket,Integer> {
    Optional<UsersBasket> findByBasketOwner_username(String name);
}
