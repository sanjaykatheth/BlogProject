package com.blog.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blogging.entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
