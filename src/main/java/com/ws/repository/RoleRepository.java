package com.ws.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ws.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	@Query("SELECT r FROM Role r WHERE r.name = :name")
	public Role findByName(@Param("name") String name);
}
