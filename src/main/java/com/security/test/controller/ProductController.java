package com.security.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.security.test.entity.Product;
import com.security.test.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to page";
	}

	@GetMapping("/products/{productId}")
	//@PreAuthorize("hasRole('ADMIN')")
	public Product getProductById(@PathVariable int productId) {
		return productService.getProductById(productId);
	}

	@GetMapping("/products")
	//@PreAuthorize("hasRole('USER')")
	public List<Product> getProducts() {
		return productService.getProducts();
	}
}
