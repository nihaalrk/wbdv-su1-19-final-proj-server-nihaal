package com.example.wbdvsu119finalprojservernihaal.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.wbdvsu119finalprojservernihaal.models.User;
import com.example.wbdvsu119finalprojservernihaal.models.UserOnReddit;
import com.example.wbdvsu119finalprojservernihaal.repositories.UserOnRedditRepository;
import com.example.wbdvsu119finalprojservernihaal.repositories.UserRepository;

@CrossOrigin(allowCredentials = "true", value = "*")
@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserOnRedditRepository userORRepository;
	
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



	
}
