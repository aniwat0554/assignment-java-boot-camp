package com.example.demo.ordering.order;

import com.example.demo.gateway.PaymentGatewayCreditPaymentInfo;
import com.example.demo.ordering.objects.*;
import com.example.demo.ordering.order.paymentRequestObject.CreditCardPaymentRequest;
import com.example.demo.ordering.order.paymentRequestObject.PaymentUpdateRequest;
import com.example.demo.users.objects.UserResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 65535)
class OrderControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private OrderService orderService;

    @Test
    @Order(1)
    @DisplayName("Check out to create order which must contain whiskies list")
    void basketCheckOut() {

        BasketPutResponse putItemResponse = testRestTemplate.postForObject("/ordering/basket/Aniwat/whisky", 3, BasketPutResponse.class);

        CheckoutResponse response = testRestTemplate.postForObject("/ordering/checkout","Aniwat", CheckoutResponse.class);

        UsersOrder responseItem = testRestTemplate.getForObject("/ordering/order/Aniwat/"+response.getCreatedOrderId(), UsersOrder.class);
        assertEquals(response.getCreatedOrderId(),responseItem.getId());
        assertEquals("BlackLabel Johny Walker",responseItem.getWhiskyOrder().getWhiskyList().get(0).getName());
        assertEquals(700,responseItem.getWhiskyOrder().getTotalPrice());
    }

    @Test
    @Order(2)
    @DisplayName("Update address and check if it is indeed updated")
    void updateAddress(){

        CheckoutResponse checkOutResponse = testRestTemplate.postForObject("/ordering/checkout","Aniwat", CheckoutResponse.class);

        UserResponse userResponse = testRestTemplate.getForObject("/users/Aniwat",UserResponse.class);
        testRestTemplate.put("/ordering/order/Aniwat/"+checkOutResponse.getCreatedOrderId()+"/address",userResponse.getUser().getAddress(), OrderAddressUpdateResponse.class);

        UsersOrder responseItem = testRestTemplate.getForObject("/ordering/order/Aniwat/"+checkOutResponse.getCreatedOrderId(), UsersOrder.class);
        assertEquals("21000",responseItem.getWhiskyOrder().getAddress().getPostcode());
    }

    @Test
    @Order(3)
    @DisplayName("Make bank payment and expect reference numbers")
    void setBankPayment(){

        BasketPutResponse putItemResponse = testRestTemplate.postForObject("/ordering/basket/Aniwat/whisky", 3, BasketPutResponse.class);

        CheckoutResponse checkOutResponse = testRestTemplate.postForObject("/ordering/checkout","Aniwat", CheckoutResponse.class);

        UserResponse userResponse = testRestTemplate.getForObject("/users/Aniwat",UserResponse.class);


        BankPayment bankPaymentResponse = testRestTemplate.postForObject("/ordering/order/Aniwat/"+checkOutResponse.getCreatedOrderId()+"/pay_by_bank",null, BankPayment.class);


        UsersOrder responseItem = testRestTemplate.getForObject("/ordering/order/Aniwat/"+checkOutResponse.getCreatedOrderId(), UsersOrder.class);
        assertEquals("1234",responseItem.getWhiskyOrder().getBankPayment().getRefNo1());
        assertEquals(PaymentMethod.BANK,responseItem.getWhiskyOrder().getPaymentMethod());

    }

    @Test
    @Order(4)
    @DisplayName("Choose delivery payment expect payment method field to reflect")
    void setOnDeliveryPayment(){

        BasketPutResponse putItemResponse = testRestTemplate.postForObject("/ordering/basket/Aniwat/whisky", 3, BasketPutResponse.class);

        CheckoutResponse checkOutResponse = testRestTemplate.postForObject("/ordering/checkout","Aniwat", CheckoutResponse.class);

        UserResponse userResponse = testRestTemplate.getForObject("/users/Aniwat",UserResponse.class);


        BankPayment bankPaymentResponse = testRestTemplate.postForObject("/ordering/order/Aniwat/"+checkOutResponse.getCreatedOrderId()+"/pay_on_delivery",null, BankPayment.class);


        UsersOrder responseItem = testRestTemplate.getForObject("/ordering/order/Aniwat/"+checkOutResponse.getCreatedOrderId(), UsersOrder.class);
        assertEquals(PaymentMethod.ONDELIVERY,responseItem.getWhiskyOrder().getPaymentMethod());

    }

    public static String read(String filePath) throws IOException{
        File file = ResourceUtils.getFile(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }
    @Test
    @Order(3)
    @DisplayName("Make credit payment and expect transaction id and url from gateway")
    void makeCreditPayment() throws IOException{

        //Mock payment API
        stubFor(
                post(urlPathEqualTo("/payment_gateway/payment_request"))
                        .willReturn(aResponse()
                                .withBody(read("classpath:paymentResponse.json"))
                                .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withStatus(200))
                        );

        BasketPutResponse putItemResponse = testRestTemplate.postForObject("/ordering/basket/Aniwat/whisky", 3, BasketPutResponse.class);

        CheckoutResponse checkOutResponse = testRestTemplate.postForObject("/ordering/checkout","Aniwat", CheckoutResponse.class);

        UserResponse userResponse = testRestTemplate.getForObject("/users/Aniwat",UserResponse.class);


        PaymentUpdateRequest paymentUpdateRequest = new PaymentUpdateRequest();
        paymentUpdateRequest.setPaymentType(PaymentMethod.CREDITCARD);
        CreditCardPaymentRequest creditCardPaymentRequest = new CreditCardPaymentRequest();
        creditCardPaymentRequest.setCardId("1234");
        creditCardPaymentRequest.setcVV("1234");
        creditCardPaymentRequest.setExpiryDate("20210101");
        creditCardPaymentRequest.setCardId("1234");
        paymentUpdateRequest.setCreditCardPaymentRequest(creditCardPaymentRequest);
        PaymentGatewayCreditPaymentInfo paymentGatewayCreditPaymentInfo = testRestTemplate.postForObject("/ordering/order/Aniwat/"+checkOutResponse.getCreatedOrderId()+"/pay_by_credit_card",paymentUpdateRequest, PaymentGatewayCreditPaymentInfo.class);

        //This method call is to simulate callback from Payment Gateway
        testRestTemplate.put("/ordering/payment/1234","Paid");
        UsersOrder responseItem = testRestTemplate.getForObject("/ordering/order/Aniwat/"+checkOutResponse.getCreatedOrderId(), UsersOrder.class);
        assertEquals("OMISE",responseItem.getWhiskyOrder().getCreditCardPayment().getPaymentGateway());
        assertEquals("1234",responseItem.getWhiskyOrder().getCreditCardPayment().getTransactionId());
        assertEquals("Paid",responseItem.getWhiskyOrder().getPaymentStatus());


        assertEquals(PaymentMethod.CREDITCARD,responseItem.getWhiskyOrder().getPaymentMethod());

    }

}