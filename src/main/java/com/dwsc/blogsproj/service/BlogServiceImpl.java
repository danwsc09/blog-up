package com.dwsc.blogsproj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwsc.blogsproj.dao.BlogDao;
import com.dwsc.blogsproj.entity.Blog;

@Service
public class BlogServiceImpl implements BlogService {

	private BlogDao blogDao;
	
	@Autowired
	public BlogServiceImpl(BlogDao theBlogDao) {
		blogDao = theBlogDao;
	}
	
	@Override
	public List<Blog> findAll() {
		// Sort sort = Sort.by("write_date");
		// List<Blog> theBlogs = blogDao.findAll(sort);
		List<Blog> theBlogs = blogDao.findAll();
		return theBlogs;
	}

	@Override
	public void saveBlog(Blog theBlog) {
		blogDao.save(theBlog);
	}

	@Override
	public void deleteBlog(int blogId) {
		blogDao.deleteById(blogId);
	}

	@Override
	public List<Blog> findByTitleLike(String title) {
		List<Blog> theBlogs = blogDao.findByTitleLike(title);
		return theBlogs;
	}

	@Override
	public Blog findById(int id) {
		Optional<Blog> item = blogDao.findById(id);
		Blog theBlog = null;
		
		if (item.isPresent()) {
			theBlog = item.get();
		}
		
		return theBlog;
	}


}
