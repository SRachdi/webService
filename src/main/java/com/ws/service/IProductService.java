package com.ws.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ws.entity.Product;

@Service
public interface IProductService {
	void addProduct(Product product);
	List<Product> getProducts();
	void deleteProduct(Product product);
	void updateProduct(Product product);
	Product getProductById(int id);

}
