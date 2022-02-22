package com.example.demo.ordering.order.paymentRequestObject;

public class CreditCardPaymentRequest{
	private String expiryDate;
	private String cVV;
	private String cardId;
	private String cardHolder;

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setcVV(String cVV) {
		this.cVV = cVV;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

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
