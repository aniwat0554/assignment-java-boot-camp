package com.example.demo.ordering.order.paymentRequestObject;

import com.example.demo.ordering.objects.BankPayment;
import com.example.demo.ordering.objects.CreditCardPayment;

public class PaymentUpdateRequest {
    private String paymentType;
    private BankPayment bankPayment;
    private CreditCardPayment creditCardPayment;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BankPayment getBankPayment() {
        return bankPayment;
    }

    public void setBankPayment(BankPayment bankPayment) {
        this.bankPayment = bankPayment;
    }

    public CreditCardPayment getCreditCardPayment() {
        return creditCardPayment;
    }

    public void setCreditCardPayment(CreditCardPayment creditCardPayment) {
        this.creditCardPayment = creditCardPayment;
    }

    public PaymentUpdateRequest() {
    }
}
