package com.example.wbdvsu119finalprojservernihaal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wbdvsu119finalprojservernihaal.models.RedditThread;
import com.example.wbdvsu119finalprojservernihaal.models.RedditUser;
import com.example.wbdvsu119finalprojservernihaal.repositories.RedditThreadRepository;
import com.example.wbdvsu119finalprojservernihaal.repositories.RedditUserRepository;


@CrossOrigin(value = "*")
@RestController
public class RedditThreadController {
	@Autowired
	RedditThreadRepository repository;
	
	@Autowired
	RedditUserRepository userRepository;
	
	@PostMapping("/api/reddit_threads")
    public RedditThread createRedditThread(@RequestBody RedditThread redditThread) {
		RedditThread thread = repository.findRedditThreadById(redditThread.getId());
		if (thread != null)
			return thread;
    	return repository.save(redditThread);
    }
    
    @GetMapping("/api/reddit_threads")
    public List<RedditThread> findAllRedditThreads() {
    	return repository.findAllRedditThreads();
    }
    
    @GetMapping("/api/reddit_threads/{id}")
    public RedditThread findRedditThreadById(@PathVariable("id") String id) {
    	RedditThread thread = repository.findRedditThreadById(id);
    	if (thread == null) {
    		return new RedditThread();
    	}
    	return thread;
    }
	
    @PutMapping("/api/reddit_threads/{id}/{username}")
    public RedditThread addCommenter(@PathVariable("id") String id, @PathVariable("username") String username) {
    	RedditThread thread = repository.findRedditThreadById(id);
    	System.out.println(id);
    	if (thread != null) {
    		RedditUser user = userRepository.findRedditUserByUsername(username);
    		if (user != null && !thread.containsUser(user)) {
    			thread.getCommenters().add(user);
    			user.getThreadsCommented().add(thread);
    			userRepository.save(user);
    			return repository.save(thread);
    		}
    	}
    	return thread;
    }
    
}
