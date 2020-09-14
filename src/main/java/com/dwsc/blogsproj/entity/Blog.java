package com.dwsc.blogsproj.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="blogs")
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="likes")
	private int likes;
	
	@Column(name="write_date")
	private String writeDate;
	
	@JoinColumn(name="author_id")
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, 
				CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private User author;
	
	@OneToMany(mappedBy = "blog", 
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Comment> comments;
	
	public Blog() {}

	public Blog(String title, String content) {
		this.title = title;
		this.content = content;
	}

	// convenience method for adding comments
	public void addComment(Comment theComment) {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		comments.add(theComment);
		theComment.setBlog(this);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", likes=" + likes + ", writeDate="
				+ writeDate + ", author=" + author.getUsername() + "]";
	}
	
}
