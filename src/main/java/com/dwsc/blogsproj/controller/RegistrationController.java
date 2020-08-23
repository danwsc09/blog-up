package com.dwsc.blogsproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dwsc.blogsproj.entity.User;
import com.dwsc.blogsproj.service.UserService;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/signup")
	public String showSignpForm(Model theModel) {
		
		User theUser = new User();
		theModel.addAttribute("user", theUser);
		
		return "signup/signup-form";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute User theUser) {
		
		theUser.setActive(1);

		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		
		theUser.setJoinDate(currentTime);
		theUser.setRoles("ROLE_USER");
		
		userService.save(theUser);
		
		return "redirect:/";
	}
}
