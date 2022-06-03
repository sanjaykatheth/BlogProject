
package com.blog.blogging.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blogging.exceptions.ApiException;
import com.blog.blogging.payloads.ApiReponse;
import com.blog.blogging.payloads.JwtAuthRequest;
import com.blog.blogging.payloads.UserDto;
import com.blog.blogging.repositories.UserRepo;
import com.blog.blogging.security.CustomUserDetailService;
import com.blog.blogging.security.JwtAuthResponse;
import com.blog.blogging.security.JwtTokenHelper;
import com.blog.blogging.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private CustomUserDetailService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepo userRepo;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@Valid @RequestBody JwtAuthRequest request) throws Exception {

		this.authenticate(request.getUsername(), request.getPassword());

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUserid(request.getUsername());
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);

	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			throw new ApiException("Invalid username or password !!");
		}

	}



	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {	
		if(this.userRepo.findByEmail(userDto.getEmail()).isPresent()){

			return new ResponseEntity(new ApiReponse("user is already present", false),HttpStatus.ALREADY_REPORTED);
		}
		UserDto registeredUser = this.userService.registerNewUser(userDto);

		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
	}

}