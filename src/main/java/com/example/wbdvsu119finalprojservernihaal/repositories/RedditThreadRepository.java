package com.example.wbdvsu119finalprojservernihaal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.wbdvsu119finalprojservernihaal.models.RedditThread;

public interface RedditThreadRepository extends CrudRepository <RedditThread, Integer> {
	
	@Query(value="SELECT entity FROM RedditThread entity")
    public List<RedditThread> findAllRedditThreads();
    
	@Query(value="SELECT entity FROM RedditThread entity WHERE id=:id")
    public RedditThread findRedditThreadById(@Param("id") String id);
	
}
