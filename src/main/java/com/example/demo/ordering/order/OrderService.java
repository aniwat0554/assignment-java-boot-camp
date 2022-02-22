package com.example.demo.ordering.order;

import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.ordering.basket.BasketService;
import com.example.demo.ordering.objects.*;
import com.example.demo.shipment.Address;
import com.example.demo.users.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private BasketService basketService;

    @Autowired
    private OrderRepository orderRepository;

    public UsersOrder getUsersOrder(int id){
        Optional<UsersOrder> result = orderRepository.findById(id);

        if (result.isPresent()){
            return result.get();
        }


        throw new OrderNotFoundException(id);
    }

    //Created to troubleshoot To Be Removed
    public UsersOrder getAnyOrder(){
        return orderRepository.findAll().get(0);
    }

    public List<UsersOrder> getAllOrder(){
        return orderRepository.findAll();
    }

    public int createNewOrder(String shopperName){
        UsersBasket usersBasket = this.basketService.getUsersBasket(shopperName);

        User shopper = usersBasket.getBasketOwner();
        WhiskyOrder order = new WhiskyOrder();

        order.setWhiskyToPurchasedWhiskyList(usersBasket.getWhiskyInBasket());
        order.setPaymentStatus("unpaid");
        BankPayment bankPayment = new BankPayment();
        bankPayment.setRefNo2("11234");
        bankPayment.setRefNo1("2134");
        order.setBankPayment(bankPayment);
        UsersOrder usersOrder = new UsersOrder(order,shopper);
        UsersOrder createdOrder = orderRepository.save(usersOrder);
        return createdOrder.getId();
    }

    public void updateOrderAddress(int usersOrderId, Address address){
        UsersOrder usersOrder = this.getUsersOrder(usersOrderId);
        usersOrder.getWhiskyOrder().setAddress(address);
        this.orderRepository.save(usersOrder);
    }

    public void updatePaymentInfo(int usersOrderId, BankPayment bankPayment) {
        UsersOrder usersOrder = this.getUsersOrder(usersOrderId);
        usersOrder.getWhiskyOrder().setBankPayment(bankPayment);
        usersOrder.getWhiskyOrder().setPaymentMethod(PaymentMethod.BANK);
        this.orderRepository.save(usersOrder);
    }
}
