package com.example.wbdvsu119finalprojservernihaal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsu119finalprojservernihaal.models.UserOnReddit;

public interface UserOnRedditRepository extends CrudRepository <UserOnReddit, Integer> {
	
	@Query(value="SELECT entity FROM UserOnReddit entity")
    public List<UserOnReddit> findAllUsersOnReddit();
    
	@Query(value="SELECT entity FROM UserOnReddit entity WHERE id=:id")
    public UserOnReddit findUserOnRedditById(@Param("id") Integer id);
	
	@Query(value="SELECT entity FROM UserOnReddit entity WHERE username=:username")
    public UserOnReddit findUserOnRedditByUsername(@Param("username") String username);
	
}
