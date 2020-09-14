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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="join_date")
	private String joinDate;
	
	@Column(name="active")
	private int active;
	
	@Column(name="roles")
	private String roles;
	
	@OneToMany(mappedBy = "author", cascade = {CascadeType.DETACH, CascadeType.MERGE,
				CascadeType.PERSIST, CascadeType.REFRESH},
				fetch = FetchType.LAZY)
	private List<Blog> blogs;
	
	@OneToMany(mappedBy = "commenter", cascade = {CascadeType.DETACH, CascadeType.MERGE,
				CascadeType.PERSIST, CascadeType.REFRESH},
				fetch = FetchType.LAZY)
	private List<Comment> comments;
	
	public User() {}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(int id, String username, String password, String email, String joinDate, int active,
			String roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.joinDate = joinDate;
		this.active = active;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", joinDate=" + joinDate + ", active=" + active + ", roles=" + roles + "]";
	}

	// a convenience method
	public void addBlog(Blog theBlog) {
		if (blogs == null) {
			blogs = new ArrayList<>();
		}
		blogs.add(theBlog);
		theBlog.setAuthor(this);
	}
	
	// convenience method for adding comment to user
	public void addComment(Comment theComment) {
		if (comments == null) {
			comments = new ArrayList<>();
		}
		comments.add(theComment);
		theComment.setCommenter(this);
	}
}
