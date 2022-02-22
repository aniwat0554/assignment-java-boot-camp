package com.example.demo.ordering.order;

import com.example.demo.ordering.objects.UsersOrder;
import com.example.demo.ordering.objects.UsersOrderDebug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<UsersOrder,Integer> {

}
