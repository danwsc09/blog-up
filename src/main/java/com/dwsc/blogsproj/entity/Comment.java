package com.dwsc.blogsproj.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="comment")
	private String content;
	
	@Column(name="write_date")
	private String writeDate;
	
	@JoinColumn(name="commenter_id")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private User commenter;
	
	@JoinColumn(name="blog_id")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private Blog blog;

	public Comment() {}
	
	public Comment(String content, String writeDate) {
		super();
		this.content = content;
		this.writeDate = writeDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", writeDate=" + writeDate + "]";
	}
	
	
	
}
