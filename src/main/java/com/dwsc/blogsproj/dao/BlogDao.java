package com.dwsc.blogsproj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dwsc.blogsproj.entity.Blog;

public interface BlogDao extends JpaRepository<Blog, Integer> {
	
	List<Blog> findByTitleLike(String title);
	
}
