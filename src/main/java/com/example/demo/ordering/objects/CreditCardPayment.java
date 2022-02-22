package com.example.demo.ordering.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CreditCardPayment{
	private String transactionId;
	private String paymentGateway;
	private float amountPaid;
	@Id
	@GeneratedValue
	private int id;
	public CreditCardPayment() {
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getPaymentGateway() {
		return paymentGateway;
	}

	public void setPaymentGateway(String paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	public float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(float amountPaid) {
		this.amountPaid = amountPaid;
	}
}
