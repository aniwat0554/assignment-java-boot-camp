package com.example.demo.ordering.order;

import com.example.demo.gateway.PaymentGatewayCreditPaymentInfo;
import com.example.demo.gateway.PaymentGateway;
import com.example.demo.messaging.OperationResult;
import com.example.demo.ordering.objects.BankPayment;
import com.example.demo.ordering.objects.CheckoutResponse;
import com.example.demo.ordering.objects.CreditCardPayment;
import com.example.demo.ordering.objects.UsersOrder;
import com.example.demo.ordering.basket.BasketService;
import com.example.demo.ordering.order.paymentRequestObject.PaymentUpdateRequest;
import com.example.demo.shipment.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderingController {

    @Autowired
    private PaymentGateway paymentGateway;
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

    @PutMapping("/ordering/order/{usersOrderId}/address")
    public OrderAddressUpdateResponse updateOrderAddress(@PathVariable int usersOrderId,@RequestBody Address address){

        orderService.updateOrderAddress(usersOrderId,address);


        OrderAddressUpdateResponse orderAddressUpdateResponse = new OrderAddressUpdateResponse();
        orderAddressUpdateResponse.setStatusMessage("success");
        return orderAddressUpdateResponse;
    }


    @GetMapping("/ordering/order/{id}")
    public UsersOrder getOrder(@PathVariable int id){
        return orderService.getUsersOrder(id);
    }

    //Turn out this is indeed needed - Not yet tested
    @GetMapping("/ordering/order")
    public OrderListResponse getAnyOrder(){
        OrderListResponse orderListResponse = new OrderListResponse();
        orderListResponse.setUsersOrderList(orderService.getAllOrder());
        return orderListResponse;
    }


    @PostMapping("/ordering/order/{usersOrderId}/pay_by_bank")
    public BankPayment payByBankGeneration(@PathVariable int usersOrderId){

        orderService.getUsersOrder(usersOrderId);

        BankPayment bankPayment = new BankPayment();
        bankPayment.setRefNo1("1234");
        bankPayment.setRefNo2("1234567");
        orderService.updatePaymentInfo(usersOrderId,bankPayment);
        return bankPayment;
    }

    @PostMapping("/ordering/order/{usersOrderId}/pay_on_delivery")
    public OperationResult payOnDelivery(@PathVariable int usersOrderId){

        orderService.getUsersOrder(usersOrderId);

        orderService.updatePaymentInfo(usersOrderId,PaymentMethod.ONDELIVERY);
        OperationResult operationResult = new OperationResult();

        return operationResult;
    }

    @PostMapping("/ordering/order/{usersOrderId}/pay_by_credit_card")
    public PaymentGatewayCreditPaymentInfo payByCreditCard(@PathVariable int usersOrderId, @RequestBody PaymentUpdateRequest paymentUpdateRequest){
        UsersOrder usersOrder = orderService.getUsersOrder(usersOrderId);
        PaymentGatewayCreditPaymentInfo creditPaymentInfo = paymentGateway.makePayment(paymentUpdateRequest.getCreditCardPaymentRequest());
        CreditCardPayment creditCardPayment = new CreditCardPayment();
        creditCardPayment.setPaymentGateway("OMISE");
        creditCardPayment.setTransactionId(creditPaymentInfo.getTransactionId());

        usersOrder.getWhiskyOrder().setCreditCardPayment(creditCardPayment);
        orderService.updatePaymentInfo(usersOrderId,creditCardPayment);
        return creditPaymentInfo;
    }

    //Callback for Payment Gateway to call to update payment status
    @PutMapping("/payment/{transactionId}")
    public OperationResult payByCreditCard(@PathVariable String transactionId,@RequestBody String status){
        orderService.updatePaymentStatus(transactionId,status);
        return new OperationResult();
    }


}
