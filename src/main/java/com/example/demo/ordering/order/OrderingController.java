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
@RequestMapping("ordering")
public class OrderingController {

    @Autowired
    private PaymentGateway paymentGateway;
    @Autowired
    private OrderService orderService;

    @Autowired
    private BasketService basketService;
    @PostMapping("/checkout")
    public CheckoutResponse basketCheckOut(@RequestBody String shopperName){
        //Implement Transaction here
        UsersOrder usersOrder = orderService.createNewOrder(shopperName);
        basketService.clearBasket(shopperName);

        CheckoutResponse checkoutResponse = new CheckoutResponse();
        checkoutResponse.setCreatedOrderId(usersOrder.getId());
        return checkoutResponse;
    }

    @PutMapping("/order/{name}/{usersOrderId}/address")
    public OrderAddressUpdateResponse updateOrderAddress(@PathVariable int usersOrderId,@PathVariable String name,@RequestBody Address address){

        orderService.updateOrderAddress(usersOrderId,address,name);


        OrderAddressUpdateResponse orderAddressUpdateResponse = new OrderAddressUpdateResponse();
        orderAddressUpdateResponse.setStatusMessage("success");
        return orderAddressUpdateResponse;
    }


    @GetMapping("/order/{name}/{id}")
    public UsersOrder getOrder(@PathVariable int id,@PathVariable String name){
        return orderService.getUsersOrder(id,name);
    }

    //Turn out this is indeed needed - Not yet tested
    @GetMapping("/order/{name}")
    public OrderListResponse getAnyOrder(@PathVariable String name){
        OrderListResponse orderListResponse = new OrderListResponse();
        orderListResponse.setUsersOrderList(orderService.getAllOrder(name));
        return orderListResponse;
    }


    @PostMapping("/order/{name}/{usersOrderId}/pay_by_bank")
    public BankPayment payByBankGeneration(@PathVariable int usersOrderId,@PathVariable String name){



        BankPayment bankPayment = new BankPayment();
        bankPayment.setRefNo1("1234");
        bankPayment.setRefNo2("1234567");
        orderService.updatePaymentInfo(usersOrderId,bankPayment,name);
        return bankPayment;
    }

    @PostMapping("/order/{name}/{usersOrderId}/pay_on_delivery")
    public OperationResult payOnDelivery(@PathVariable int usersOrderId,@PathVariable String name){


        orderService.updatePaymentInfo(usersOrderId,PaymentMethod.ONDELIVERY,name);
        OperationResult operationResult = new OperationResult();

        return operationResult;
    }

    @PostMapping("/order/{name}/{usersOrderId}/pay_by_credit_card")
    public CreditCardPayment payByCreditCard(@PathVariable int usersOrderId,@PathVariable String name, @RequestBody PaymentUpdateRequest paymentUpdateRequest){

        PaymentGatewayCreditPaymentInfo creditPaymentInfo = orderService.makePayment(paymentUpdateRequest);
        CreditCardPayment creditCardPayment = new CreditCardPayment();
        creditCardPayment.setPaymentGateway(creditPaymentInfo.getPaymentGatewayName());
        creditCardPayment.setTransactionId(creditPaymentInfo.getTransactionId());

        orderService.updatePaymentInfo(usersOrderId,creditCardPayment,name);
        return creditCardPayment;
    }

    //Callback for Payment Gateway to call to update payment status
    @PutMapping("/payment/{transactionId}")
    public OperationResult updatePaymentStatus(@PathVariable String transactionId,@RequestBody String status){
        orderService.updatePaymentStatus(transactionId,status);
        return new OperationResult();
    }


}
