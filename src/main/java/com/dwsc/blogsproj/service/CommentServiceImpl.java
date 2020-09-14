package com.dwsc.blogsproj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dwsc.blogsproj.dao.CommentDao;
import com.dwsc.blogsproj.entity.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentDao commentDao;
	
	@Autowired
	public CommentServiceImpl(CommentDao theCommentDao) {
		commentDao = theCommentDao;
	}
	
	@Override
	public void saveComment(Comment theComment) {
		commentDao.save(theComment);
		
	}
}
