package com.example.wbdvsu119finalprojservernihaal.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="reddit_threads")
public class RedditThread {

	private String id;
	private String subreddit;
	private String title;
	private String date;
	private Integer upvotes;
	private Integer comments;
	private String permalink;
	
	private RedditUser author;
	private List<RedditUser> commenters;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubreddit() {
		return subreddit;
	}
	public void setSubreddit(String subreddit) {
		this.subreddit = subreddit;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(Integer upvotes) {
		this.upvotes = upvotes;
	}
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	public RedditUser getAuthor() {
		return author;
	}
	public void setAuthor(RedditUser author) {
		this.author = author;
	}
	public List<RedditUser> getCommenters() {
		return commenters;
	}
	public void setCommenters(List<RedditUser> commenters) {
		this.commenters = commenters;
	}
	
}
