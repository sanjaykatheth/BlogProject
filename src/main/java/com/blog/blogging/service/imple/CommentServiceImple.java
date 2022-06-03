package com.blog.blogging.service.imple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blogging.entities.Comment;
import com.blog.blogging.entities.Post;
import com.blog.blogging.exceptions.ResourceNotFoundException;
import com.blog.blogging.payloads.CommentDto;
import com.blog.blogging.repositories.CommentRepo;
import com.blog.blogging.repositories.PostRepository;
import com.blog.blogging.service.CommentService;

@Service
public class CommentServiceImple implements CommentService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMap;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));

		Comment comment = this.modelMap.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment saveComment = this.commentRepo.save(comment);


		return this.modelMap.map(saveComment, CommentDto.class);
	}

	@Override
	public void delete(Integer commentId) {


		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "commentId", commentId));;
		this.commentRepo.delete(comment);
	}

}
