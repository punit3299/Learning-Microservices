package com.customer.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class Product {
	
	private int productId;
	private String name;
	private int price;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int productId, String name, int price) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
