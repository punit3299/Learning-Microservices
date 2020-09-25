package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.ProductRepository;
import com.product.models.Product;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductRepository productRepo;
	
	@GetMapping("/hello")
	public String homepage() {
		return "Hello Product";
	}
	
	@PostMapping("addProduct")
	public ResponseEntity<Product> addProduct() {
		Product p=new Product("Cricket Bat", 1000);
		return new ResponseEntity<Product>(productRepo.save(p), HttpStatus.OK);
	}
	
	@GetMapping("/getProduct")
	public ResponseEntity<Product> getProduct() {
		Product p=productRepo.findById(1).get();
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}

}
