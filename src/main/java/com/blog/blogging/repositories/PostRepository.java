package com.blog.blogging.repositories;

import java.util.List;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blogging.entities.Category;
import com.blog.blogging.entities.Post;
import com.blog.blogging.entities.User;

@Repository
public interface PostRepository  extends JpaRepository<Post, Integer>{

	 List<Post> findByUser(User user);
	 List<Post> findByCategory(Category category);
	}
