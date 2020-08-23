package com.dwsc.blogsproj.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dwsc.blogsproj.entity.User;

public interface UserService extends UserDetailsService {
	List<User> findAll();
	
	User findById(int theId);
	
	User findByUsername(String username);
	
	void save(User theUser);
}
