package com.example.wbdvsu119finalprojservernihaal.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="reddit_users")
public class RedditUser {

	private String username;
	private Integer commentKarma;
	private String linkKarma;
	
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

	public String getLinkKarma() {
		return linkKarma;
	}

	public void setLinkKarma(String linkKarma) {
		this.linkKarma = linkKarma;
	}

	public List<RedditThread> getThreadsCommented() {
		return threadsCommented;
	}

	public void setThreadsCommented(List<RedditThread> threadsCommented) {
		this.threadsCommented = threadsCommented;
	}
	
	
	
}
