package com.example.wbdvsu119finalprojservernihaal.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="reddit_threads")
public class RedditThread {
	
	@Id
	private String id;
	private String subreddit;
	private String title;
	private String date;
	private Integer upvotes;
	private Integer comments;
	private String permalink;
	private String thumbnail;
	private String text;
	
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@ManyToOne
	private RedditUser author;
	@ManyToMany(mappedBy="threadsCommented")
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
	
	public boolean containsUser(RedditUser user) {
		for(RedditUser commenter : this.commenters) {
			if (commenter.getUsername().equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}
	
}
