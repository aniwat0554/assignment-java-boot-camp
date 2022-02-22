package com.example.demo.gateway;

public class PaymentGatewayCreditPaymentInfo {
    private String transactionId;
    private String otpUrl;

    public PaymentGatewayCreditPaymentInfo() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOtpUrl() {
        return otpUrl;
    }

    public void setOtpUrl(String otpUrl) {
        this.otpUrl = otpUrl;
    }
}
