package com.example.demo.users.objects;

import com.example.demo.shipment.Address;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements UserDetails , Serializable {

	@OneToOne(optional = false,cascade= CascadeType.ALL)
	private Address address;
	private String username;
	private final boolean enabled = true;

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(cascade= CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"))
	private Set<Role> authorities = new HashSet<>();
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
		return this.authorities;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
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
