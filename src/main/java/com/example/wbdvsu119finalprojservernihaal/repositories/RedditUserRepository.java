package com.example.wbdvsu119finalprojservernihaal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsu119finalprojservernihaal.models.RedditUser;

public interface RedditUserRepository extends CrudRepository <RedditUser, Integer> {
	
	@Query(value="SELECT entity FROM RedditUser entity")
    public List<RedditUser> findAllRedditUsers();
    
	@Query(value="SELECT entity FROM RedditUser entity WHERE username=:username")
    public RedditUser findRedditUserByUsername(@Param("username") String username);
	
}
