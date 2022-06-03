package com.blog.blogging.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.blogging.entities.User;
import com.blog.blogging.exceptions.ResourceNotFoundException;
import com.blog.blogging.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByEmail(username)
				 .orElseThrow(()-> new ResourceNotFoundException("user", "not found"+ username,0));
		return user;
	}

}
