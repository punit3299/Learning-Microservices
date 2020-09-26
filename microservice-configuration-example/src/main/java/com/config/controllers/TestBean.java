package com.config.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value="test")
public class TestBean {
	
	private int id;
	private String name;
	
	public TestBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
