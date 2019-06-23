package com.example.wbdvsu119finalprojservernihaal.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wbdvsu119finalprojservernihaal.models.RedditThread;
import com.example.wbdvsu119finalprojservernihaal.models.RedditUser;
import com.example.wbdvsu119finalprojservernihaal.repositories.RedditUserRepository;


@CrossOrigin(value = "*")
@RestController
public class RedditUserController {
	@Autowired
    RedditUserRepository repository;
	
	@PostMapping("/api/reddit_users")
    public RedditUser createRedditUser(@RequestBody RedditUser redditUser) {
		RedditUser user = repository.findRedditUserByUsername(redditUser.getUsername());
		if (user != null)
			return user;
    	return repository.save(redditUser);
    }
    
    @GetMapping("/api/reddit_users")
    public List<RedditUser> findAllRedditUsers() {
    	return repository.findAllRedditUsers();
    }
    
    @GetMapping("/api/reddit_users/{username}")
    public RedditUser findRedditUserByUsername(@PathVariable("username") String username) {
    	RedditUser user = repository.findRedditUserByUsername(username);
    	if (user == null) {
    		return new RedditUser();
    	}
    	return user;
    }
    
    @GetMapping("/api/reddit_users/{username}/reddit_threads")
    public List<RedditThread> threadsCommentedOn(@PathVariable("username") String username) {
    	RedditUser user = repository.findRedditUserByUsername(username);
    	if (user == null) {
    		return new ArrayList<>();
    	}
    	List<RedditThread> threads = user.getThreadsCommented();
    	return threads;
    }
	
}
