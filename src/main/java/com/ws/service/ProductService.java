package com.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws.entity.Product;
import com.ws.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

	@Autowired
	ProductRepository pr;

	@Override
	public void addProduct(Product product) {
		pr.save(product);
	}

	@Override
	public List<Product> getProducts() {
		return (List<Product>) pr.findAll();
	}

	@Override
	public void deleteProduct(Product product) {
		pr.delete(product);
	}

	@Override
	public void updateProduct(Product product) { 
		pr.save(product);
	}

	@Override
	public Product getProductById(int id) {
		return pr.findById(id).get();
	}
}
