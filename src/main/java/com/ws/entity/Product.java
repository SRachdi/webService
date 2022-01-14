package com.ws.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	@Temporal(TemporalType.DATE)
	private Date expireDate;
	
	private int stock;

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

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Product(int id, String name, Date expireDate, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.expireDate = expireDate;
		this.stock = stock;
	}

	public Product(String name, Date expireDate, int stock) {
		super();
		this.name = name;
		this.expireDate = expireDate;
		this.stock = stock;
	}

	public Product() {
		super();
	}
	
	

}
