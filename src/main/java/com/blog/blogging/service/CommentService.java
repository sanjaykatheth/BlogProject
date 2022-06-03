package com.blog.blogging.service;

import org.springframework.stereotype.Service;

import com.blog.blogging.payloads.CommentDto;

@Service
public interface CommentService {

	 CommentDto createComment(CommentDto commentDto, Integer postId);
	 void delete(Integer commentId);
}
