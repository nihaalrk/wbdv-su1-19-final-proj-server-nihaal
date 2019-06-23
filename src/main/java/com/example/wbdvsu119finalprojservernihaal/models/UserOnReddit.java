package com.example.wbdvsu119finalprojservernihaal.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users_on_reddit")
public class UserOnReddit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	private String state;
	private String email;
	
	private String redditUsername;
	
	public List<RedditThread> getLikedThreads() {
		return likedThreads;
	}
	public void setLikedThreads(List<RedditThread> likedThreads) {
		this.likedThreads = likedThreads;
	}
	@ManyToMany
	@JoinTable(
        name="users_reddit_threads",
        joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="reddit_thread_id", referencedColumnName="id"))
	private List<RedditThread> likedThreads;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRedditUsername() {
		return redditUsername;
	}
	public void setRedditUsername(String redditUsername) {
		this.redditUsername = redditUsername;
	}
	
}
