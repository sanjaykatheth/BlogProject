package com.blog.blogging.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UserDto {

	@NotBlank	
	@Size(min=4,message="username must be ")
	private String name;

	@Email(message="Email address is not valid")
	@NotBlank
	private String email;

	@NotBlank
	@Size(min=4,max=12 ,message="username must be ")
	private String password;

	@NotBlank
	private String about;

	private String message;
	private Set<RoleDto> role=new HashSet<>();


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public UserDto(String name, String email, String password, String about) {
		super();

		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Set<RoleDto> getRole() {
		return role;
	}
	public void setRole(Set<RoleDto> role) {
		this.role = role;
	}
	public UserDto(@NotBlank @Size(min = 4, message = "username must be ") String name) {
		super();
		this.name = name;
	}


}
