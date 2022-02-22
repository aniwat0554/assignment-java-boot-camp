package com.example.demo.ordering.order;

import com.example.demo.messaging.OperationResult;
import com.example.demo.ordering.basket.BasketService;
import com.example.demo.ordering.objects.BankPayment;
import com.example.demo.ordering.objects.CheckoutResponse;
import com.example.demo.ordering.objects.UsersOrder;
import com.example.demo.ordering.order.paymentRequestObject.PaymentUpdateRequest;
import com.example.demo.shipment.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //Created for troubleshooting To Be Removed
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

}
