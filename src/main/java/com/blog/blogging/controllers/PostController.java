package com.blog.blogging.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blogging.payloads.ApiReponse;
import com.blog.blogging.payloads.PostDto;
import com.blog.blogging.payloads.PostsResponse;
import com.blog.blogging.service.FileService;
import com.blog.blogging.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {


	@Autowired
	private PostService  postService;


	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{catId}/posts")
	public ResponseEntity<?> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer catId)
	{

		PostDto createdPost=this.postService.createPost(postDto, userId, catId);
		return new ResponseEntity(createdPost,HttpStatus.CREATED);
	}
	@RequestMapping(value="/user/{userId}/posts" , method=RequestMethod.GET)
	public ResponseEntity<?> getPostsByUser(@PathVariable Integer userId) {
		List<PostDto> posts=this.postService.getPostsByUser(userId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}


	@RequestMapping(value="/category/{catId}/posts", method = RequestMethod.GET)
	public ResponseEntity<?> getPostsByCategory(@PathVariable Integer catId) {
		List<PostDto> posts=this.postService.getPostsByCategory(catId);
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}


	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<?> getAllPosts(
			@RequestParam(value="pageNo",defaultValue ="0",required = false) Integer pageNo,
			@RequestParam(value="pageSize",defaultValue ="5",required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue ="postId",required = false) String sortBy

			)
	{
		PostsResponse posts = this.postService.getAllPosts(pageNo,pageSize,sortBy);
		return new ResponseEntity(posts,HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<?> getPostById(@PathVariable Integer postId){
		PostDto	post=this.postService.getPostById(postId);
		return new ResponseEntity(post,HttpStatus.OK);

	}

	@DeleteMapping("posts/{postId}")
	public ApiReponse deletePosts(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ApiReponse("post is succefully deleted", true);
	}

	@PutMapping("posts/{postId}")
	public ResponseEntity<?> updatePosts(@RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updated=this.postService.updatePost(postDto, postId);
		return new ResponseEntity(updated,HttpStatus.OK);

	}

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<?> searchPostsByTitle(@PathVariable("keywords") String keywords){
		List<PostDto> result = this.postService.searchPosts(keywords);
		return new ResponseEntity(result,HttpStatus.OK);

	}

	@PostMapping("/post/image/{postId}")
	public ResponseEntity<?> uploadPostImage(
			@RequestParam("image") MultipartFile imageFile,
			@PathVariable("postId") Integer postId ){
		PostDto data = this.postService.getPostById(postId);

		String fileName = this.fileService.uploadImage(path, imageFile);
		data.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(data, postId);
		return new ResponseEntity(updatePost,HttpStatus.OK);

	}

	@GetMapping(value="/images/{imageName}" ,produces=org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public void downLoadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException
	{
		InputStream res = this.fileService.getResource(path, imageName);
		response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(res, response.getOutputStream());
	}

}


