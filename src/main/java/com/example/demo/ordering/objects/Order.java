package com.example.demo.ordering.objects;

import java.util.List;

public class Order{
	private BankPayment bankPayment;
	private List<Object> address;
	private CreditCardPayment creditCardPayment;
	private String paymentMethod;
	private List<Object> whiskyList;
	private String paymentStatus;

	public void setBankPayment(BankPayment bankPayment){
		this.bankPayment = bankPayment;
	}

	public BankPayment getBankPayment(){
		return bankPayment;
	}

	public void setAddress(List<Object> address){
		this.address = address;
	}

	public List<Object> getAddress(){
		return address;
	}

	public void setCreditCardPayment(CreditCardPayment creditCardPayment){
		this.creditCardPayment = creditCardPayment;
	}

	public CreditCardPayment getCreditCardPayment(){
		return creditCardPayment;
	}

	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	public void setWhiskyList(List<Object> whiskyList){
		this.whiskyList = whiskyList;
	}

	public List<Object> getWhiskyList(){
		return whiskyList;
	}

	public void setPaymentStatus(String paymentStatus){
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentStatus(){
		return paymentStatus;
	}
}