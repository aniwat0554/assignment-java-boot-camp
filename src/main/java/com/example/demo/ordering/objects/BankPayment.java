package com.example.demo.ordering.objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BankPayment{
	private String refNo2;
	private String refNo1;
	@Id
	@GeneratedValue
	private int id;
	public BankPayment() {
	}

	public void setRefNo2(String refNo2){
		this.refNo2 = refNo2;
	}

	public String getRefNo2(){
		return refNo2;
	}

	public void setRefNo1(String refNo1){
		this.refNo1 = refNo1;
	}

	public String getRefNo1(){
		return refNo1;
	}
}
