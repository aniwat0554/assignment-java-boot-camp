package com.example.demo.users.objects;

import com.example.demo.shipment.Address;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements UserDetails {

	@OneToOne(optional = false,cascade= CascadeType.ALL)
	private Address address;
	private String username;
	private final boolean enabled = true;

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

	public User(Address address, String username) {
		this.address = address;
		this.username = username;
	}

	public User(Address address, String username,String password) {
		this.address = address;
		this.username = username;
		this.password = password;
	}

	public User() {
	}

	@Id
	@GeneratedValue
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setUsername(String username){
		this.username = username;
	}

	@Override
	public Set<Role> getAuthorities() {
		return null;
	}


	@Override
	public String getPassword() {
		return this.password;
	}

	public String getUsername(){
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
