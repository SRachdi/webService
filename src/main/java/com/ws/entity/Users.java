package com.ws.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	@Column(unique=true)
	private String token;
	
	@Column(unique=true)
	private String email;
	
	private boolean enabled;
	
	@Temporal(TemporalType.DATE)
	private Date dateCreated;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "idUser"),
			inverseJoinColumns = @JoinColumn(name = "idRole")
			)
	private Set<Role> roles = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Set<Role>  getRoles() {
		return roles;
	}

	public void setRoles(Set<Role>  roles) {
		this.roles = roles;
	}

	public Users(int id, String firstName, String lastName, String username, String password, String token,
			String email, boolean enabled, Date dateCreated, Set<Role>  roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.token = token;
		this.email = email;
		this.enabled = enabled;
		this.dateCreated = dateCreated;
		this.roles = roles;
	}

	public Users(String firstName, String lastName, String username, String password, String token, String email,
			boolean enabled, Date dateCreated, Set<Role>  roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.token = token;
		this.email = email;
		this.enabled = enabled;
		this.dateCreated = dateCreated;
		this.roles = roles;
	}

	public Users(int id, String firstName, String lastName, String username, String password, String token,
			String email, boolean enabled, Date dateCreated) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.token = token;
		this.email = email;
		this.enabled = enabled;
		this.dateCreated = dateCreated;
	}

	public Users() {
		super();
	}
	
	
	
}
