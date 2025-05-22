package com.security.test.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.security.test.entity.Product;

@Service
public class ProductService {
	private final List<Product> products;

	public ProductService() {
		products = IntStream.rangeClosed(1, 10).mapToObj(i -> new Product(i, "Product " + i, (i % 10) + 1, i * 5.0))
				.collect(Collectors.toList());
	}

	public List<Product> getProducts() {
		return products;
	}
	
	public Product getProductById(int id) {
        return products.stream()
            .filter(product -> product.getProductId() == id)
            .findFirst()
            .orElse(null);
    }
}
