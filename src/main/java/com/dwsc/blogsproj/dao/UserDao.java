package com.dwsc.blogsproj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwsc.blogsproj.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
}
