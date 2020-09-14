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
import org.springframework.web.bind.annotation.RequestParam;

import com.dwsc.blogsproj.entity.Blog;
import com.dwsc.blogsproj.entity.Comment;
import com.dwsc.blogsproj.entity.User;
import com.dwsc.blogsproj.service.BlogService;
import com.dwsc.blogsproj.service.CommentService;
import com.dwsc.blogsproj.service.UserService;

@Controller
public class HomeController {
	
	private BlogService blogService;
	private UserService userService;
	private CommentService commentService;
	
	@Autowired
	public HomeController(BlogService theBlogService,
			UserService theUserService,
			CommentService theCommentService) {
		blogService = theBlogService;
		userService = theUserService;
		commentService = theCommentService;
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
	
	@GetMapping("/view/{blogId}")
	public String showSingleBlog(@PathVariable int blogId, Model theModel) {
		
		Blog theBlog = blogService.findById(blogId);
		List<Comment> theComments = theBlog.getComments();
		
		theModel.addAttribute("blog", theBlog);
		theModel.addAttribute("comments", theComments);
		
		// for logging each comment
		for (Comment comment : theComments) {
			System.out.println("Commented user: " + comment.getCommenter().getUsername());
			System.out.println("Comment: " + comment.getContent());
			System.out.println("Comment date: " + comment.getWriteDate());
		}
		
		Comment theComment = new Comment();
		theComment.setId(0);
		theModel.addAttribute("currentComment", theComment);
		
		return "view/blog-content";
	}
	
	@PostMapping("/submitComment/{blogId}")
	public String submitComment(@PathVariable int blogId, 
			@ModelAttribute Comment theComment) {
		
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		// generate date
		if (theComment.getId() == 0) {
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = 
					new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			
			theComment.setWriteDate(currentTime);	
		}
		System.out.println(theComment);
		// link to user and blog
		User theUser = userService.findByUsername(username);
		Blog theBlog = blogService.findById(blogId);
		
		theUser.addComment(theComment);
		theBlog.addComment(theComment);
		
		// save comment to DB
		commentService.saveComment(theComment);
		
		return "redirect:/view/" + blogId;
	}
}
