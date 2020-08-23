package com.dwsc.blogsproj.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dwsc.blogsproj.dao.UserDao;
import com.dwsc.blogsproj.entity.User;

@Service
public class UserServiceImpl implements UserService {

	// Inject DAO
	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public List<User> findAll() {

		return userDao.findAll();
	}

	@Override
	public User findById(int theId) {
		Optional<User> foundUser = userDao.findById(theId);
		if (!foundUser.isPresent()) return null;
		
		return foundUser.get();
	}

	@Override
	public User findByUsername(String username) {
		User foundUser = userDao.findByUsername(username);
		
		return foundUser;
	}

	@Override
	public void save(User theUser) {
		
		// hash user's password		
		theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
		
		User dbUser = userDao.save(theUser);
		System.out.println("=================");
		System.out.println("saved user's id: " + dbUser.getId());
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// System.out.println("==================\nin loadbyusername (user service impl)");
		// System.out.println("Looking for username: " + username);
		User user = userDao.findByUsername(username);
		// System.out.println("found user: " + user);
		
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		List<String> roles = Arrays.asList(user.getRoles().split(","));
		// System.out.println("MAP ROLES TO AUTHORITIES: " + roles);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(roles));
	}
	
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<String> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}
	
}
