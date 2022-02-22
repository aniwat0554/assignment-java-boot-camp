package com.example.demo.ordering.objects;

public class CheckoutResponse {
    private String statusMessage;

    private int createdOrderId;

    public int getCreatedOrderId() {
        return createdOrderId;
    }

    public void setCreatedOrderId(int createdOrderId) {
        this.createdOrderId = createdOrderId;
    }

    public CheckoutResponse() {
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
