package com.demo.model;

import javax.persistence.Embeddable;

@Embeddable
public class Publication {

	Publication() {}
	
	public Publication(String name, String address, float rating) {
		super();
		this.name = name;
		this.address = address;
		this.rating = rating;
	}

	String name;
	String address;
	float rating;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	
}
