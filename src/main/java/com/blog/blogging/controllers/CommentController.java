package com.blog.blogging.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogging.payloads.ApiReponse;
import com.blog.blogging.payloads.CommentDto;
import com.blog.blogging.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<?> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId){

		CommentDto commentDto = this.commentService.createComment(comment, postId);
		return new ResponseEntity(commentDto,HttpStatus.CREATED);

	}


	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<?> createComment(@PathVariable Integer commentId){
		
		this.commentService.delete(commentId);
		return new ResponseEntity(new ApiReponse("Deleted comment", false), HttpStatus.OK);
		
}

}