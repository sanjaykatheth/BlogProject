package com.blog.blogging.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogging.payloads.ApiReponse;
import com.blog.blogging.payloads.UserDto;
import com.blog.blogging.repositories.UserRepo;
import com.blog.blogging.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<?> createdUser(@Valid @RequestBody  UserDto userDto){
		boolean flag=false;
		Optional<String> userData=this.userRepo.findByEmails(userDto.getEmail());
		if(userData.isPresent()) {
			return ResponseEntity.ok(new ApiReponse("user is presend", flag));
		}

		return new ResponseEntity<>(this.userService.createUser(userDto),HttpStatus.CREATED);
	}


	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId)
	{

		UserDto updatedUser=this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId)
	{
		this.userService.deleteUser(userId);
		return new ResponseEntity(new ApiReponse("user delete success", true),HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUser());

	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getSingleUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));

	}



}
