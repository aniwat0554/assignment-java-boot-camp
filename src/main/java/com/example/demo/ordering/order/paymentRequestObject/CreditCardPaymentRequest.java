package com.example.demo.ordering.order.paymentRequestObject;

public class CreditCardPaymentRequest{
	private String expiryDate;
	private String cVV;
	private String cardId;
	private String cardHolder;

	public CreditCardPaymentRequest() {
	}

	public String getExpiryDate(){
		return expiryDate;
	}

	public String getCVV(){
		return cVV;
	}

	public String getCardId(){
		return cardId;
	}

	public String getCardHolder(){
		return cardHolder;
	}
}
