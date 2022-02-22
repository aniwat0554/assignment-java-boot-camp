package com.example.demo.ordering.objects;

import com.example.demo.pricing.Price;

import javax.persistence.*;

@Entity
public class PurchasedWhisky{
	@Id
	@GeneratedValue
	private int id;

	@OneToOne(optional = false,cascade= CascadeType.ALL)
	private Price price;
	private String review;
	private String tasteProfile;
	private String name;
	private int degree;
	private String description;


	public PurchasedWhisky() {
	}

	public PurchasedWhisky(String name, Price price) {
		this.price = price;
		this.name = name;
	}

	public PurchasedWhisky(Price price, String review, String tasteProfile, String name, int degree, String description) {
		this.price = price;
		this.review = review;
		this.tasteProfile = tasteProfile;
		this.name = name;
		this.degree = degree;
		this.description = description;
	}

	public void setPrice(Price price){
		this.price = price;
	}

	public Price getPrice(){
		return price;
	}

	public void setReview(String review){
		this.review = review;
	}

	public String getReview(){
		return review;
	}

	public void setTasteProfile(String tasteProfile){
		this.tasteProfile = tasteProfile;
	}

	public String getTasteProfile(){
		return tasteProfile;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDegree(int degree){
		this.degree = degree;
	}

	public int getDegree(){
		return degree;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
