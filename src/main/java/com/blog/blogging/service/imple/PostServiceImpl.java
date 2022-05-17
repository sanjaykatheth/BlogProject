package com.blog.blogging.service.imple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blogging.entities.Category;
import com.blog.blogging.entities.Post;
import com.blog.blogging.entities.User;
import com.blog.blogging.exceptions.ResourceNotFoundException;
import com.blog.blogging.payloads.PostDto;
import com.blog.blogging.payloads.PostsResponse;
import com.blog.blogging.repositories.CategoryRepo;
import com.blog.blogging.repositories.PostRepository;
import com.blog.blogging.repositories.UserRepo;
import com.blog.blogging.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo catRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {

		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "user id", userId));
		Category cat=this.catRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("user", "user id", userId));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(cat);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	
	}

	@Override
	public void deletePost(Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
          this.postRepo.delete(post);
	}

	@Override
	public PostsResponse getAllPosts(Integer pageNo,Integer pageSize,String sortBy) {
		
	    PageRequest p = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Post> pageData=this.postRepo.findAll(p);
		List<Post> allPost=pageData.getContent();
		List<PostDto> allPostDto=allPost.stream().map((post)->this.modelMapper.map(allPost, PostDto.class)).collect(Collectors.toList());
		PostsResponse postRes=new PostsResponse();	
		postRes.setContents(allPostDto);
		postRes.setPageNo(pageData.getNumber());
		postRes.setPageSize(pageData.getSize());
		postRes.setTotalElements(pageData.getTotalElements());
		postRes.setTotalPages(pageData.getTotalPages());
		postRes.setLastPage(pageData.isLast());
		return postRes;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postid", postId));
		PostDto dto=this.modelMapper.map(post, PostDto.class);
		return dto;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {

		Category category=this.catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("cat", "catId", categoryId));
		List<Post> posts=this.postRepo.findByCategory(category);
		List list=new ArrayList<>();
		for(Post data:posts)
		{
			PostDto postDto=this.modelMapper.map(data, PostDto.class);
			list.add(postDto);
		}
		return list;

	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User users=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userid", userId));

		List<Post> posts=this.postRepo.findByUser(users);
		PostDto postDto;
		List list=new ArrayList<>();
		for(Post data:posts)
		{
			postDto=this.modelMapper.map(data, PostDto.class);
			list.add(postDto);
		}
		return list;



	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		return null;
	}



}
