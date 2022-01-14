package com.ws.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ws.entity.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

	@Query("SELECT u FROM Users u WHERE u.username = :username")
	public Users getUserByUsername(@Param("username") String username);
	
	@Query("SELECT u.username FROM Users u WHERE u.username = :username")
	public String uniqueTestUsername(@Param("username") String username);
	
	@Query("SELECT u.username FROM Users u WHERE u.username = :username AND u.id <> :id")
	public String uniqueTestUsernameWithId(@Param("id") int id, @Param("username") String username);
	
	@Query("SELECT u.email FROM Users u WHERE u.email = :email")
	public String uniqueTestEmail(@Param("email") String email);
	
	@Query("SELECT u.email FROM Users u WHERE u.email = :email AND u.id <> :id")
	public String uniqueTestEmailWithId(@Param("id") int id, @Param("email") String email);
	
	@Query("SELECT u FROM Users u WHERE u.token = :token")
	public Users getUserByToken(@Param("token") String token);
}
