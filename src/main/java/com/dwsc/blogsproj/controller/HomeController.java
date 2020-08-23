package com.dwsc.blogsproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwsc.blogsproj.entity.Blog;
import com.dwsc.blogsproj.service.BlogService;

@Controller
public class HomeController {
	
	private BlogService blogService;
	
	@Autowired
	public HomeController(BlogService theBlogService) {
		blogService = theBlogService;
	}
	
	@GetMapping()
	public String showBlogs(Model theModel) {
		List<Blog> theBlogs = blogService.findAll();
		theModel.addAttribute("blogs", theBlogs);
		return "home/blogs-list";
	}
	
	@PostMapping("/search")
	public String searchBlogs(@RequestParam("searchWord") String keyword, Model theModel) {
		keyword = "%" + keyword + "%";
		
		List<Blog> theBlogs = blogService.findByTitleLike(keyword);
		
		if (theBlogs != null) {
			theModel.addAttribute("blogs", theBlogs);
		}
		return "home/blogs-list";
	}
	
}
