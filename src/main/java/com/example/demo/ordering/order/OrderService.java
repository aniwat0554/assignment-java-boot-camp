package com.example.demo.ordering.order;

import com.example.demo.gateway.PaymentGateway;
import com.example.demo.gateway.PaymentGatewayCreditPaymentInfo;
import com.example.demo.ordering.basket.BasketService;
import com.example.demo.ordering.objects.*;
import com.example.demo.ordering.order.paymentRequestObject.PaymentUpdateRequest;
import com.example.demo.shipment.Address;
import com.example.demo.users.objects.User;
import com.example.demo.whiskies.objects.Whisky;
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

    @Autowired
    private PaymentGateway paymentGateway;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    public UsersOrder getUsersOrder(int id,String shopperName){
        Optional<UsersOrder> result = orderRepository.findById(id);

        if(!result.get().getShopper().getUsername().equals(shopperName)){
            throw new OrderNotFoundException(id);
        }
        if (result.isPresent()){
            return result.get();
        }


        throw new OrderNotFoundException(id);
    }

    //Created to troubleshoot To Be Removed
    public UsersOrder getAnyOrder(){
        return orderRepository.findAll().get(0);
    }

    public List<UsersOrder> getAllOrder(String username){
        return orderRepository.findByShopper_username(username);
    }

    public UsersOrder createNewOrder(String shopperName){
        UsersBasket usersBasket = this.basketService.getUsersBasket(shopperName);

        User shopper = usersBasket.getBasketOwner();
        WhiskyOrder order = prepareOrder(usersBasket.getWhiskyInBasket());
        UsersOrder usersOrder = new UsersOrder(order,shopper);
        usersOrder.getWhiskyOrder().setTotalPrice(usersBasket.getTotalPrice());
        UsersOrder createdOrder = orderRepository.save(usersOrder);
        return createdOrder;
    }
    private WhiskyOrder prepareOrder(List<Whisky> whiskyList){
        WhiskyOrder order = new WhiskyOrder();

        order.setWhiskyToPurchasedWhiskyList(whiskyList);
        order.setPaymentStatus("unpaid");
        return order;
    }

    public void updateOrderAddress(int usersOrderId, Address address,String shopperName){
        UsersOrder usersOrder = this.getUsersOrder(usersOrderId,shopperName);
        usersOrder.getWhiskyOrder().setAddress(address);
        this.orderRepository.save(usersOrder);
    }

    public void updatePaymentInfo(int usersOrderId, BankPayment bankPayment,String shopperName) {
        UsersOrder usersOrder = this.getUsersOrder(usersOrderId,shopperName);
        usersOrder.getWhiskyOrder().setBankPayment(bankPayment);
        usersOrder.getWhiskyOrder().setPaymentMethod(PaymentMethod.BANK);
        this.orderRepository.save(usersOrder);
    }

    public void updatePaymentInfo(int usersOrderId, CreditCardPayment creditCardPayment,String shopperName) {
        UsersOrder usersOrder = this.getUsersOrder(usersOrderId,shopperName);
        usersOrder.getWhiskyOrder().setCreditCardPayment(creditCardPayment);
        usersOrder.getWhiskyOrder().setPaymentMethod(PaymentMethod.CREDITCARD);
        this.orderRepository.save(usersOrder);
    }

    public void updatePaymentInfo(int usersOrderId, String paymentMethod,String shopperName) {
        UsersOrder usersOrder = this.getUsersOrder(usersOrderId,shopperName);
        usersOrder.getWhiskyOrder().setPaymentMethod(paymentMethod);
        this.orderRepository.save(usersOrder);
    }

    public void updatePaymentStatus(String transactionId,String status){
        UsersOrder usersOrder = orderRepository.findByWhiskyOrder_CreditCardPayment_transactionId(transactionId).get();
        usersOrder.getWhiskyOrder().setPaymentStatus(status);
        orderRepository.save(usersOrder);

    }

    public PaymentGatewayCreditPaymentInfo makePayment(PaymentUpdateRequest paymentUpdateRequest){
        PaymentGatewayCreditPaymentInfo creditPaymentInfo = paymentGateway.makePayment(paymentUpdateRequest.getCreditCardPaymentRequest());
        return creditPaymentInfo;
    }
}
