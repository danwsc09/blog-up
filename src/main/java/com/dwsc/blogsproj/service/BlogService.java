package com.dwsc.blogsproj.service;

import java.util.List;

import com.dwsc.blogsproj.entity.Blog;

public interface BlogService {
	
	List<Blog> findAll();
	
	Blog findById(int id);
	
	void saveBlog(Blog theBlog);
	
	void deleteBlog(int blogId);
	
	List<Blog> findByTitleLike(String title);
}
