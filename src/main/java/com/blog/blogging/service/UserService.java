package com.blog.blogging.service;

import java.util.List;

import com.blog.blogging.payloads.UserDto;


public interface UserService {

	
	 UserDto createUser(UserDto userDto);
	 UserDto updateUser(UserDto userDto,Integer userId);
	 UserDto getUserById(Integer userId);
	 
	 List<UserDto> getAllUser();
	 void deleteUser(Integer userId);
	 
}
