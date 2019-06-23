package com.example.wbdvsu119finalprojservernihaal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsu119finalprojservernihaal.models.User;

public interface UserRepository extends CrudRepository <User, Integer> {
	
	@Query(value="SELECT entity FROM User entity")
    public List<User> findAllUsers();
    
	@Query(value="SELECT entity FROM User entity WHERE id=:id")
    public User findUserById(@Param("id") Integer id);
	
	@Query(value="SELECT entity FROM User entity WHERE username=:username")
    public User findUserByUsername(@Param("username") String username);
	
}
