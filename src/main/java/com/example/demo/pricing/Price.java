package com.example.demo.pricing;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Price{
	private int basePrice;
	private String unit;
	private int discount;
	private int netPrice;
	@Id
	@GeneratedValue
	private int id;

	public Price() {
	}

	public Price(int basePrice, String unit, int discount) {
		this.unit = unit;
		this.discount = discount;
		this.basePrice = basePrice;
	}

	public void setUnit(String unit){
		this.unit = unit;
	}

	public String getUnit(){
		return unit;
	}

	public void setDiscount(int discount){
		this.discount = discount;
	}

	public int getDiscount(){
		return discount;
	}

	public int getNetPrice(){
		return this.basePrice-this.discount;
	}

	public void setBasePrice(int basePrice){
		this.basePrice = basePrice;
	}

	public int getBasePrice(){
		return basePrice;
	}
}
