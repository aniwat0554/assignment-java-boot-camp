package com.example.demo;

import com.example.demo.gateway.PaymentGatewayCreditPaymentInfo;
import com.example.demo.ordering.objects.BasketPutResponse;
import com.example.demo.ordering.objects.BasketResponse;
import com.example.demo.ordering.objects.CheckoutResponse;
import com.example.demo.ordering.objects.UsersOrder;
import com.example.demo.ordering.order.OrderAddressUpdateResponse;
import com.example.demo.ordering.order.OrderListResponse;
import com.example.demo.ordering.order.PaymentMethod;
import com.example.demo.ordering.order.paymentRequestObject.CreditCardPaymentRequest;
import com.example.demo.ordering.order.paymentRequestObject.PaymentUpdateRequest;
import com.example.demo.users.objects.UserResponse;
import com.example.demo.whiskies.objects.WhiskiesResponse;
import com.example.demo.whiskies.objects.Whisky;
import com.example.demo.whiskies.objects.WhiskyDetailResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 65535)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    @Order(1)
    @DisplayName("Searching : Search for Johny Walker and get 5 Johny Walker whiskies")
    void searchJohnyWalker() {
        WhiskiesResponse response = testRestTemplate.getForObject("/whiskies?name=Johny Walker",WhiskiesResponse.class);
        assertEquals(10,response.getTotal());
        assertEquals(5,response.getWhiskiesResponse().size());

    }

    @Test
    @Order(2)
    @DisplayName("Selecting : get red label data")
    void getRedLabel() {
        WhiskiesResponse response = testRestTemplate.getForObject("/whiskies?name=Johny Walker",WhiskiesResponse.class);
        Whisky redLabel = response.getWhiskiesResponse().stream().filter(whisky -> "RedLabel Johny Walker".equals(whisky.getName())).findAny().get();

        WhiskyDetailResponse responseWhisky = testRestTemplate.getForObject("/whiskies/"+redLabel.getName(),WhiskyDetailResponse.class);
        assertEquals("RedLabel Johny Walker",responseWhisky.getWhiskyDetail().getName());
    }

    @Test
    @Order(3)
    @DisplayName("Put item in basket and see basket")
    void putInBasket(){
        WhiskyDetailResponse responseWhisky = testRestTemplate.getForObject("/whiskies/RedLabel Johny Walker",WhiskyDetailResponse.class);
        assertEquals("RedLabel Johny Walker",responseWhisky.getWhiskyDetail().getName());
        Whisky redLabel = responseWhisky.getWhiskyDetail();
        BasketPutResponse putItemResponse = testRestTemplate.postForObject("/ordering/basket/Aniwat/whisky", redLabel.getId(), BasketPutResponse.class);
        BasketResponse getResponse = testRestTemplate.getForObject("/ordering/basket/Aniwat", BasketResponse.class);
        assertEquals(1,getResponse.getWhiskyList().size());

        assertEquals(false,getResponse.getWhiskyList().stream().filter(whisky -> "RedLabel Johny Walker".equals(whisky.getName())).findAny().isEmpty());
        assertEquals(600,getResponse.getTotalPrice());
    }

    @Test
    @Order(4)
    @DisplayName("Check out to create order which must contain red label")
    void basketCheckOut() {

        CheckoutResponse response = testRestTemplate.postForObject("/ordering/checkout","Aniwat", CheckoutResponse.class);

        UsersOrder responseItem = testRestTemplate.getForObject("/ordering/order/Aniwat/"+response.getCreatedOrderId(), UsersOrder.class);
        assertEquals(response.getCreatedOrderId(),responseItem.getId());
        assertEquals("RedLabel Johny Walker",responseItem.getWhiskyOrder().getWhiskyList().get(0).getName());
        assertEquals(600,responseItem.getWhiskyOrder().getTotalPrice());
    }

    @Test
    @Order(5)
    @DisplayName("Update address and check if it is indeed updated")
    void updateAddress(){
        OrderListResponse orderListResponse = testRestTemplate.getForObject("/ordering/order/Aniwat",OrderListResponse.class);

        UserResponse userResponse = testRestTemplate.getForObject("/users/Aniwat",UserResponse.class);
        testRestTemplate.put("/ordering/order/Aniwat/"+orderListResponse.getUsersOrderList().get(0).getId()+"/address",userResponse.getUser().getAddress(), OrderAddressUpdateResponse.class);

        UsersOrder responseItem = testRestTemplate.getForObject("/ordering/order/Aniwat/"+orderListResponse.getUsersOrderList().get(0).getId(), UsersOrder.class);
        assertEquals("21000",responseItem.getWhiskyOrder().getAddress().getPostcode());
    }


    @Test
    @Order(6)
    @DisplayName("Make credit payment and see order detail")
    void setCreditPayment() throws IOException{

        //Mock payment API
        stubFor(
                post(urlPathEqualTo("/payment_gateway/payment_request"))
                        .willReturn(aResponse()
                                .withBody(read("classpath:paymentResponse.json"))
                                .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withStatus(200))
        );

        OrderListResponse orderListResponse = testRestTemplate.getForObject("/ordering/order/Aniwat",OrderListResponse.class);

        PaymentUpdateRequest paymentUpdateRequest = new PaymentUpdateRequest();
        paymentUpdateRequest.setPaymentType(PaymentMethod.CREDITCARD);
        CreditCardPaymentRequest creditCardPaymentRequest = new CreditCardPaymentRequest();
        creditCardPaymentRequest.setCardId("1234");
        creditCardPaymentRequest.setcVV("1234");
        creditCardPaymentRequest.setExpiryDate("20210101");
        creditCardPaymentRequest.setCardId("1234");
        paymentUpdateRequest.setCreditCardPaymentRequest(creditCardPaymentRequest);
        PaymentGatewayCreditPaymentInfo paymentGatewayCreditPaymentInfo = testRestTemplate.postForObject("/ordering/order/Aniwat/"+orderListResponse.getUsersOrderList().get(0).getId()+"/pay_by_credit_card",paymentUpdateRequest, PaymentGatewayCreditPaymentInfo.class);

        //This method call is to simulate callback from Payment Gateway
        testRestTemplate.put("/ordering/payment/1234","Paid");
        UsersOrder responseItem = testRestTemplate.getForObject("/ordering/order/Aniwat/"+orderListResponse.getUsersOrderList().get(0).getId(), UsersOrder.class);
        assertEquals("OMISE",responseItem.getWhiskyOrder().getCreditCardPayment().getPaymentGateway());
        assertEquals("1234",responseItem.getWhiskyOrder().getCreditCardPayment().getTransactionId());
        assertEquals("Paid",responseItem.getWhiskyOrder().getPaymentStatus());


        assertEquals(PaymentMethod.CREDITCARD,responseItem.getWhiskyOrder().getPaymentMethod());

    }

    public static String read(String filePath) throws IOException {
        File file = ResourceUtils.getFile(filePath);
        return new String(Files.readAllBytes(file.toPath()));
    }

}
