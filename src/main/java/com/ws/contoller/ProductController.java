package com.ws.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ws.entity.Product;
import com.ws.service.IProductService;

@RestController
public class ProductController {
	
	@Autowired
	IProductService ps;
	
	@GetMapping(value = "/getProducts")
	@ResponseBody
	public List<Product> getProducts() {
		return ps.getProducts();
	}
	
	@PostMapping(value = "/addProduct")
	@ResponseBody
	public void addProduct(@RequestBody Product product) {
		 ps.addProduct(product);
	}
	
	@DeleteMapping(value = "/deleteProduct/{id}")
	@ResponseBody
	public void deleteProduct(@PathVariable("id") int id) {
		Product product = ps.getProductById(id);
		ps.deleteProduct(product);
	}
	
	@PutMapping(value = "/updateProduct/{id}")
	@ResponseBody
	public void updateProduct(@RequestBody Product product, @PathVariable("id") int id) {
		product.setId(id);
		ps.updateProduct(product);
	}

}
