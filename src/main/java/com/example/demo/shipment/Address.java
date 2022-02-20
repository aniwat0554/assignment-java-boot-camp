package com.example.demo.shipment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address{
	private String province;
	private String subdistrict;
	private String district;
	private String postcode;
	private String houseNo;

	public Address() {
	}

	public Address(String province, String subdistrict, String district, String postcode, String houseNo) {
		this.province = province;
		this.subdistrict = subdistrict;
		this.district = district;
		this.postcode = postcode;
		this.houseNo = houseNo;
	}

	@Id
	@GeneratedValue
	private int id;

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return province;
	}

	public void setSubdistrict(String subdistrict){
		this.subdistrict = subdistrict;
	}

	public String getSubdistrict(){
		return subdistrict;
	}

	public void setDistrict(String district){
		this.district = district;
	}

	public String getDistrict(){
		return district;
	}

	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public String getPostcode(){
		return postcode;
	}

	public void setHouseNo(String houseNo){
		this.houseNo = houseNo;
	}

	public String getHouseNo(){
		return houseNo;
	}
}
