package com.example.wbdvsu119finalprojservernihaal.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="reddit_users")
public class RedditUser {

	@Id
	private String username;
	private Integer commentKarma;
	private Integer linkKarma;
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(
	        name="reddit_threads_users",
	        joinColumns=@JoinColumn(name="reddit_user_username", referencedColumnName="username"),
	        inverseJoinColumns=@JoinColumn(name="reddit_thread_id", referencedColumnName="id"))
	private List<RedditThread> threadsCommented;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCommentKarma() {
		return commentKarma;
	}

	public void setCommentKarma(Integer commentKarma) {
		this.commentKarma = commentKarma;
	}

	public Integer getLinkKarma() {
		return linkKarma;
	}

	public void setLinkKarma(Integer linkKarma) {
		this.linkKarma = linkKarma;
	}

	public List<RedditThread> getThreadsCommented() {
		return threadsCommented;
	}

	public void setThreadsCommented(List<RedditThread> threadsCommented) {
		this.threadsCommented = threadsCommented;
	}
	
	
	
}
