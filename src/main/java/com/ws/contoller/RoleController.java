package com.ws.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping ;
import org.springframework.web.bind.annotation.GetMapping;      
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody; 
import org.springframework.web.bind.annotation.RestController;

import com.ws.entity.Role; 
import com.ws.service.IRoleService;

@RestController      
public class RoleController {
	
	@Autowired
	IRoleService rs;
	
	@GetMapping(value = "/getRoles")
	@ResponseBody
	public List<Role> getRoles() {
		return rs.getRoles();
	}
	
	@GetMapping(value = "/getRole/{id}")
	@ResponseBody
	public Role getRole(@PathVariable("id") int id) {
		return rs.getRoleById(id);
	}
	
	@PostMapping("/addRole")
	@ResponseBody
	public void addRole(@RequestBody Role role) {
		rs.addRole(role);
	}
	
	@DeleteMapping("/deleteRole/{id}")
	@ResponseBody
	public void deleteRole(@PathVariable("id") int id) {
		Role role = rs.getRoleById(id);
		rs.deleteRole(role);
	}
	
	@PutMapping("/updateRole/{id}")
	public void updateRole(@RequestBody Role role, @PathVariable("id") int id) {
		/*Role r = rs.getRoleById(id);
		r.setName(role.getName());
		rs.updateRole(role);*/
		role.setId(id);
		rs.updateRole(role);
	}
}
