package com.example.demo.gateway;

import com.example.demo.ordering.order.paymentRequestObject.CreditCardPaymentRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MockPaymentGatewayAPI {

    @PostMapping("/payment_gateway/payment_request")
    public PaymentGatewayCreditPaymentInfo makePayment(CreditCardPaymentRequest creditCardPaymentRequest){
        //Check creditCardInfo
        PaymentGatewayCreditPaymentInfo creditPaymentInfo = new PaymentGatewayCreditPaymentInfo();
        creditPaymentInfo.setOtpUrl("https://www.google.com");
        creditPaymentInfo.setTransactionId("12345678");
        return creditPaymentInfo;

    }
}
