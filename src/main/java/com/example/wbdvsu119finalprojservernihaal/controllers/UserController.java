package com.example.wbdvsu119finalprojservernihaal.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wbdvsu119finalprojservernihaal.models.RedditThread;
import com.example.wbdvsu119finalprojservernihaal.models.User;
import com.example.wbdvsu119finalprojservernihaal.models.UserOnReddit;
import com.example.wbdvsu119finalprojservernihaal.repositories.RedditThreadRepository;
import com.example.wbdvsu119finalprojservernihaal.repositories.UserOnRedditRepository;
import com.example.wbdvsu119finalprojservernihaal.repositories.UserRepository;

@CrossOrigin(allowCredentials = "true", value = "*")
@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserOnRedditRepository userORRepository;
	@Autowired
	RedditThreadRepository threadRepository;
	
	@GetMapping("/api/users")
    public List<User> findAllUsers() {
    	return userRepository.findAllUsers();
    }
	
	@GetMapping("/api/usersOnReddit")
    public List<UserOnReddit> findAllUsersOnReddit() {
    	return userORRepository.findAllUsersOnReddit();
    }
    
    @GetMapping("/api/users/{id}")
    public User findUserById(@PathVariable("id") Integer id) {
    	return userRepository.findUserById(id);
    }
    
    @GetMapping("/api/usersOnReddit/{id}")
    public UserOnReddit findUserOnRedditById(@PathVariable("id") Integer id) {
    	return userORRepository.findUserOnRedditById(id);
    }
    
    @PostMapping("/api/users")
	public User register(@RequestBody User user, HttpSession session) {
    	if (userRepository.findUserByUsername(user.getUsername()) == null
    		&& userORRepository.findUserOnRedditByUsername(user.getUsername()) == null) {
    		session.setAttribute("currentUser", user);
    		session.setAttribute("type", "normal");
        	return userRepository.save(user);
    	}
    	return null;
    }
    
    @PostMapping("/api/usersOnReddit")
	public UserOnReddit register(@RequestBody UserOnReddit user, HttpSession session) {
    	if (userORRepository.findUserOnRedditByUsername(user.getUsername()) == null
    		&& userRepository.findUserByUsername(user.getUsername()) == null) {
    		session.setAttribute("currentUser", user);
    		session.setAttribute("type", "onReddit");
        	return userORRepository.save(user);
    	}
    	return null;
    }
    
    @GetMapping("/api/profile")
    public Object profile(HttpSession session) {
    	String type = (String) session.getAttribute("type");
    	if (type != null) {
    		if (type.equals("normal")) {
    			User user = (User) session.getAttribute("currentUser");
    	    	if (user != null)
    	    		return user;
    		} else {
    			UserOnReddit user = (UserOnReddit) session.getAttribute("currentUser");
    	    	if (user != null)
    	    		return user;
    		}
    	}
    	return new User();
    }
    
    @PostMapping("/api/logout")
    public void logout (HttpSession session) {
    	session.invalidate();
    }
    
    @PostMapping("/api/login")
    public Object login(@RequestBody User credentials, HttpSession session) {
		List<User> users = userRepository.findAllUsers();
		for (User user : users) {
			if(user.getUsername() != null && user.getUsername().equals(credentials.getUsername())
				&& user.getPassword().equals(credentials.getPassword())) {
				session.setAttribute("currentUser", user);
				session.setAttribute("type", "normal");
				return user;
			}
	    }
		
		List<UserOnReddit> usersOnReddit = userORRepository.findAllUsersOnReddit();
		for (UserOnReddit user : usersOnReddit) {
			if(user.getUsername() != null && user.getUsername().equals(credentials.getUsername())
				&& user.getPassword().equals(credentials.getPassword())) {
				session.setAttribute("currentUser", user);
				session.setAttribute("type", "onReddit");
				return user;
			}
	    }
    	
	    return new User();
	}

    @PutMapping("/api/users/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Integer id, HttpSession session) {
    	User user = userRepository.findUserById(id);
    	user.setFirstName(newUser.getFirstName());
    	user.setLastName(newUser.getLastName());
    	user.setEmail(newUser.getEmail());
    	user.setState(newUser.getState());
    	session.setAttribute("currentUser", user);
    	return userRepository.save(user);
    }
    
    @PutMapping("/api/usersOnReddit/{id}")
    public UserOnReddit updateUserOnReddit(@RequestBody UserOnReddit newUser, @PathVariable Integer id, HttpSession session) {
    	UserOnReddit user = userORRepository.findUserOnRedditById(id);
    	user.setFirstName(newUser.getFirstName());
    	user.setLastName(newUser.getLastName());
    	user.setEmail(newUser.getEmail());
    	user.setState(newUser.getState());
    	session.setAttribute("currentUser", user);
    	return userORRepository.save(user);
    }
    
    @DeleteMapping("/api/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}
    
    @DeleteMapping("/api/usersOnReddit/{id}")
	public void deleteUserOnReddit(@PathVariable Integer id) {
		userORRepository.deleteById(id);
	}
    
    @PutMapping("api/usersOnReddit/{id}/threads/{thread_id}")
    public UserOnReddit addThreadLike(@PathVariable Integer id, @PathVariable String thread_id, HttpSession session) {
    	UserOnReddit user = userORRepository.findUserOnRedditById(id);
    	RedditThread thread = threadRepository.findRedditThreadById(thread_id);
    	if (thread != null) {
    		user.getLikedThreads().add(thread);
    		session.setAttribute("currentUser", user);
    	}
    	return userORRepository.save(user);
    }
    		
    		
}
