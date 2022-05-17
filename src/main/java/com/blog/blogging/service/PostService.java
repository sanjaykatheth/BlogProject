package com.blog.blogging.service;

import java.util.List;

import com.blog.blogging.payloads.PostDto;

public interface PostService {
	
	PostDto       createPost(PostDto postDto,Integer postId,Integer catId);
	PostDto       updatePost(PostDto postDto,Integer postId);
	void       deletePost(Integer postId);
	List<PostDto> getAllPosts();
	PostDto       getPostById(Integer postId);
	List<PostDto> getPostsByCategory(Integer categoryId);
	List<PostDto> getPostsByUser(Integer userId);
	List<PostDto> searchPosts(String keyword);


}
