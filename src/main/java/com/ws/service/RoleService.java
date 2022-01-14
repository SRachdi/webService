package com.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws.entity.Role;
import com.ws.repository.RoleRepository;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	RoleRepository rp;

	@Override
	public void addRole(Role role) {
		rp.save(role);
	}

	@Override
	public List<Role> getRoles() {
		return (List<Role>) rp.findAll();
	}

	@Override
	public void deleteRole(Role role) {
		rp.delete(role);
	}
	
	@Override
	public void updateRole(Role role) {
		rp.save(role);
	}

	@Override
	public Role getRoleById(int id) {
		Role role = rp.findById(id).get();
		return role;
	}

	@Override
	public Role getRoleByName(String name) {
		Role role = rp.findByName(name);
		return role;
	}

	
	
}
