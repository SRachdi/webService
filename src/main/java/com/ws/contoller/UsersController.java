package com.ws.contoller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.ws.entity.Users;
import com.ws.service.IUsersService;
import com.ws.service.MyUserDetailsService;
import com.ws.utils.AuthenticationRequest;
import com.ws.utils.AuthenticationResponse;
import com.ws.utils.JwtUtil;
import com.ws.utils.MyUserDetails;

@RestController
public class UsersController {

	@Autowired
	IUsersService us;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@GetMapping(value = "/getUsers")
	@ResponseBody
	public List<Users> getUsers() {
		return us.getUsers();
	}
	
	@GetMapping(value = "/getUser/{id}")
	@ResponseBody
	public Users getUser(@PathVariable("id") int id) {
		return us.getUserById(id);
	}
	
	@PostMapping(value = "/addUser")
	@ResponseBody
	public int addUser(@RequestBody Users user) {
		int test = us.testUnique(user);
		if(test != 0) {
			return test;
		}
		us.addUser(user);
		return 0;
	}
	
	@PostMapping(value = "/signUp")
	@ResponseBody
	public int signUp(@RequestBody Users user) {
		int test = us.testUnique(user);
		if(test != 0) {
			return test;
		}
		us.signUp(user);
		return 0;
	}
	
	@GetMapping(value = "/confirm/{token}")
	@ResponseBody
	public String confirm(@PathVariable("token") String token) {
		us.confirm(token);
		return "your account is active now";
	}
	
	@DeleteMapping("/deleteUser/{id}")
	@ResponseBody
	public void deleteUser(@PathVariable("id") int id) {
		Users user = us.getUserById(id);
		us.deleteUser(user);
	}
	
	@PutMapping("/updateUser/{id}")
	@ResponseBody
	public int updateUser(@RequestBody Users user, @PathVariable("id") int id) {
		user.setId(id);
		int test = us.testUniqueWithId(user);
		if(test != 0) {
			return test;
		}
		us.updateUser(user);
		return 0;
	}
	
	@PostMapping(value = "/authenticate")
	@ResponseBody
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}
