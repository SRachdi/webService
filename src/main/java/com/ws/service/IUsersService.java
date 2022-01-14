package com.ws.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ws.entity.Users;

@Service
public interface IUsersService {
	void addUser(Users user);
	List<Users> getUsers();
	void deleteUser(Users user);
	void updateUser(Users user);
	void signUp(Users user);
	int testUnique(Users user);
	Users getUserById(int id);
	void confirm(String token);
	int testUniqueWithId(Users user);
}
