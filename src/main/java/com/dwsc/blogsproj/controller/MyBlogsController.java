package com.dwsc.blogsproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwsc.blogsproj.entity.Blog;
import com.dwsc.blogsproj.entity.User;
import com.dwsc.blogsproj.service.BlogService;
import com.dwsc.blogsproj.service.UserService;

@Controller
@RequestMapping("/myblogs")
public class MyBlogsController {
	
	
	private UserService userService;
	private BlogService blogService;
	
	@Autowired
	public MyBlogsController(UserService theUserService, BlogService theBlogService) {
		userService = theUserService;
		blogService = theBlogService;
	}
	
	// Shows list of blogs
	@GetMapping()
	public String showMyBlogs(Model theModel) {
		
		// get current user who's logged in
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		User theUser = userService.findByUsername(username);
		
		List<Blog> theBlogs = theUser.getBlogs();
		theModel.addAttribute("blogs", theBlogs);
		
		return "myblogs/list";
	}
	
	
	// Returns a template to write a blog
	@GetMapping("/write")
	public String writeBlog(Model theModel) {
		
		Blog theBlog = new Blog();
		theBlog.setId(0);
		theModel.addAttribute("blog", theBlog);
		
		return "myblogs/write";
	}
	
	@PostMapping("/submit")
	public String submitBlog(@ModelAttribute("blog") Blog theBlog) {

		System.out.println("========");
		System.out.println("In submit endpoint");
		System.out.println("content: " + theBlog.getContent());
		System.out.println("date: " + theBlog.getWriteDate());
		System.out.println("========");
		
		if (theBlog.getId() == 0) {
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = 
					new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			
			
			theBlog.setWriteDate(currentTime);
			theBlog.setLikes(0);	
		}
		
		
		// Get user from username
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}

		User theUser = userService.findByUsername(username);
		
		// add blog to user, then redirect
		theUser.addBlog(theBlog);
		
		blogService.saveBlog(theBlog);
		
		return "redirect:/myblogs";
	}
	
	// View each blog
	@GetMapping("/view/{blogId}")
	public String viewBlog(@PathVariable("blogId") int theId, Model theModel) {
		
		Blog theBlog = blogService.findById(theId);
		theModel.addAttribute("blog", theBlog);
		
		return "myblogs/view";
	}
	
	// Delete mapping for blog
	@GetMapping("/delete/{blogId}")
	public String deleteBlog(@PathVariable("blogId") int theId) {
		
		blogService.deleteBlog(theId);
		
		return "redirect:/myblogs";
	}
	
	// Edit blog
	@GetMapping("/edit")
	public String updateBlog(@RequestParam(name="blogId") int theId,
			Model theModel) {
		
		// find by blog id and username
		// if blog is found but not under the same user, then deny access
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		Blog theBlog = blogService.findById(theId);
		if (theBlog == null) {
			// throw BlogNotFoundException
			// for now, transfer to error page
			return "access-denied";
		}
		if (!theBlog.getAuthor().getUsername().equals(username)) {
			// throw exception. 
			// for now - transfer to error page
			return "access-denied";
		
		}
		
		System.out.println("In /edit mapping");
		System.out.println("blog id: " + theBlog.getId());
		System.out.println("blog content: " + theBlog.getContent());
		System.out.println("blog date: " + theBlog.getWriteDate());
		
		theModel.addAttribute("blog", theBlog);
		
		return "myblogs/write";
	}
	
}
