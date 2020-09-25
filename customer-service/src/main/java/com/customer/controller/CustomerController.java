package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.models.Product;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/hello")
	public String homepage() {
		String data=restTemplate.getForObject("http://localhost:8082/product/hello", String.class);
		System.out.println(data);
		return "Hello Customer"+data;
	}
	
	@GetMapping("/product")
	public Product getCustomerProduct() {
		Product product=restTemplate.getForObject("http://localhost:8082/product/getProduct", Product.class);
		return product;
	}

}
