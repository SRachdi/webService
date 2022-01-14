package com.ws.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.fasterxml.uuid.Generators;

import com.ws.entity.Users;
import com.ws.repository.UsersRepository;

@Service
public class UsersService implements IUsersService {

	@Autowired
	UsersRepository ur;
	
	/*@Autowired
	EmailService es;*/
	
	@Override
	public void addUser(Users user) {
		String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(pw_hash);
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		user.setDateCreated(date);
		String token = "token-" + Generators.nameBasedGenerator().generate(user.getUsername());
		user.setToken(token);
		ur.save(user);
	}

	@Override
	public List<Users> getUsers() {
		return (List<Users>) ur.findAll();
	}

	@Override
	public void deleteUser(Users user) {
		ur.delete(user);
	}

	@Override
	public void updateUser(Users user) {
		String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(pw_hash);
		ur.save(user);
	}

	@Override
	public void signUp(Users user) {
		String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(pw_hash);
		user.setEnabled(false);
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		user.setDateCreated(date);
		String token = "token-" + Generators.nameBasedGenerator().generate(user.getUsername());
		user.setToken(token);
		ur.save(user);
		//es.sendMailUserConfirmation(user.getEmail(), "Confirmation de l'inscription", token);
	}

	@Override
	public int testUnique(Users user) {
		if(ur.uniqueTestEmail(user.getEmail()) != null) {
			return 1;
		}
		if(ur.uniqueTestUsername(user.getUsername()) != null ) {
			return 2;
		}
		return 0;
	}
	
	@Override
	public int testUniqueWithId(Users user) {
		if(ur.uniqueTestEmailWithId(user.getId(), user.getEmail()) != null) {
			return 1;
		}
		if(ur.uniqueTestUsernameWithId(user.getId(), user.getUsername()) != null ) {
			return 2;
		}
		return 0;
	}
	
	
	@Override
	public Users getUserById(int id) {
		return ur.findById(id).get();
	}
	
	@Override 
	public void confirm(String token) {
		Users user = ur.getUserByToken(token);
		user.setEnabled(true);
		ur.save(user);
	}

}
