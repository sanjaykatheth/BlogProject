package com.blog.blogging.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blog.blogging.entities.User;
import com.blog.blogging.payloads.UserDto;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
@Query( value = "select distinct(email) from user where email = ?1", nativeQuery = true)
	Optional<String> findByEmail(String email);
}
