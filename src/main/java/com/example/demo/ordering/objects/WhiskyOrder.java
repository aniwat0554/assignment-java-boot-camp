package com.example.demo.ordering.objects;

import com.example.demo.pricing.Price;
import com.example.demo.shipment.Address;
import com.example.demo.whiskies.objects.Whisky;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WhiskyOrder {


	@OneToOne(optional = true,cascade = CascadeType.ALL)
	private Address address;

	private String paymentMethod;
	@OneToMany(cascade = CascadeType.ALL)
	private List<PurchasedWhisky> whiskyList;
	private String paymentStatus;
	@Id
	@GeneratedValue
	private int id;

	public WhiskyOrder() {
	}
	@OneToOne(optional = true,cascade= CascadeType.ALL)
	private CreditCardPayment creditCardPayment;

	@OneToOne(optional = true,cascade= CascadeType.ALL)
	private BankPayment bankPayment;

	public void setBankPayment(BankPayment bankPayment){
		this.bankPayment = bankPayment;
	}

	public BankPayment getBankPayment(){
		return bankPayment;
	}

	public void setCreditCardPayment(CreditCardPayment creditCardPayment){
		this.creditCardPayment = creditCardPayment;
	}

	public CreditCardPayment getCreditCardPayment(){
		return creditCardPayment;
	}


	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}



	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	public void setWhiskyToPurchasedWhiskyList(List<Whisky> selectedWhisky){
		ArrayList<PurchasedWhisky> purchasedWhiskyList = new ArrayList<PurchasedWhisky>();
		for(Whisky whisky : selectedWhisky){
			PurchasedWhisky purchasedWhisky = new PurchasedWhisky();
			purchasedWhisky.setDegree(whisky.getDegree());
			purchasedWhisky.setDescription(whisky.getDescription());
			purchasedWhisky.setName(whisky.getName());

			purchasedWhisky.setReview(whisky.getReview());
			purchasedWhisky.setTasteProfile(whisky.getTasteProfile());
			Price price = new Price();
			price.setBasePrice(whisky.getPrice().getBasePrice());
			price.setDiscount(whisky.getPrice().getDiscount());
			price.setUnit(whisky.getPrice().getUnit());
			purchasedWhisky.setPrice(price);
			purchasedWhiskyList.add(purchasedWhisky);
		}
		this.setWhiskyList(purchasedWhiskyList);
	}
	public void setWhiskyList(List<PurchasedWhisky> whiskyList){
		this.whiskyList = whiskyList;
	}

	public List<PurchasedWhisky> getWhiskyList(){
		return whiskyList;
	}

	public void setPaymentStatus(String paymentStatus){
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentStatus(){
		return paymentStatus;
	}
}