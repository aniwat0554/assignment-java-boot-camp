package com.example.demo.ordering.objects;

public class CreditCardPayment{
	private String expiryDate;
	private String holderName;
	private String cvv;
	private String cardNo;

	public void setExpiryDate(String expiryDate){
		this.expiryDate = expiryDate;
	}

	public String getExpiryDate(){
		return expiryDate;
	}

	public void setHolderName(String holderName){
		this.holderName = holderName;
	}

	public String getHolderName(){
		return holderName;
	}

	public void setCvv(String cvv){
		this.cvv = cvv;
	}

	public String getCvv(){
		return cvv;
	}

	public void setCardNo(String cardNo){
		this.cardNo = cardNo;
	}

	public String getCardNo(){
		return cardNo;
	}
}
