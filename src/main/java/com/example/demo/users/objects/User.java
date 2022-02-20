package com.example.demo.users.objects;

import com.example.demo.shipment.Address;

import javax.persistence.*;

@Entity
public class User{

	@OneToOne(optional = false,cascade= CascadeType.ALL)
	private Address address;
	private String username;

	public User(Address address, String username) {
		this.address = address;
		this.username = username;
	}

	public User() {
	}

	@Id
	@GeneratedValue
	private int id;

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}
