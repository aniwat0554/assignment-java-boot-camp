package com.example.demo.pricing;

public class Price{
	private int basePrice;
	private String unit;
	private int discount;
	private int netPrice;


	public Price(int basePrice,String unit, int discount) {
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
