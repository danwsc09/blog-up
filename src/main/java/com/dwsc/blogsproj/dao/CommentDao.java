package com.dwsc.blogsproj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwsc.blogsproj.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer> {

}
