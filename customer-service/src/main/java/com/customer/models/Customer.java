package com.customer.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Customer {
	
	@Id
	private int customerId;
	private String name;
	private int age;

}
