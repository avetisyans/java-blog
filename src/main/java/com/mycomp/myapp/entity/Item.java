package com.mycomp.myapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String description;

	@Column(name="publish_date")
	private Date publishDate;

	private String link;
	
	@ManyToOne
	@JoinColumn(name="blog_id")
	private Blog blog;

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
