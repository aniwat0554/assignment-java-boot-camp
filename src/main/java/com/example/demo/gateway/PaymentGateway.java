package com.example.demo.gateway;

import com.example.demo.ordering.objects.CreditCardPayment;
import com.example.demo.ordering.order.paymentRequestObject.CreditCardPaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentGateway {

    @Value("${server.port}")
    private int serverPort;

    public PaymentGatewayCreditPaymentInfo makePayment(CreditCardPaymentRequest paymentUpdateRequest){
        //RestTemplate restTemplate = new RestTemplate();
        //PaymentGatewayCreditPaymentInfo creditPaymentInfo = restTemplate.postForObject("http://localhost:"+serverPort+"/payment_gateway/payment_request",paymentUpdateRequest, PaymentGatewayCreditPaymentInfo.class);
        PaymentGatewayCreditPaymentInfo creditPaymentInfo = new PaymentGatewayCreditPaymentInfo();
        creditPaymentInfo.setOtpUrl("https://www.google.com");
        creditPaymentInfo.setTransactionId("1234");
        return creditPaymentInfo;
    }
}
