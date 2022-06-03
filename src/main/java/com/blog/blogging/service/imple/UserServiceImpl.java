package com.blog.blogging.service.imple;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.blogging.entities.Role;
import com.blog.blogging.entities.User;
import com.blog.blogging.exceptions.ResourceNotFoundException;
import com.blog.blogging.payloads.AppConstant;
import com.blog.blogging.payloads.UserDto;
import com.blog.blogging.repositories.RoleRepository;
import com.blog.blogging.repositories.UserRepo;
import com.blog.blogging.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncode;
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User saveUser= this.userRepo.save(user);	
		return this.userToUserDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("User", "id", userId));
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		User updatedUser=this.userRepo.save(user);
		return this.userToUserDto(updatedUser);


	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException
				("User","ID",userId));
		UserDto userDto=this.userToUserDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> listOfUsers=this.userRepo.findAll();
		List<UserDto> listDtoUsers=listOfUsers.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
		return listDtoUsers;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "id",userId));
		this.userRepo.delete(user);

	}

	private User dtoToUser(UserDto userDto)
	{

		User user= this.modelMapper.map(userDto, User.class);
		//User user=new User();
		//		user.setName(userDto.getName());
		//		user.setEmail(userDto.getEmail());
		//		user.setAbout(userDto.getAbout());
		//		user.setPassword(userDto.getPassword());
		return user;
	}

	private UserDto userToUserDto(User user) {

		UserDto userDto=this.modelMapper.map(user, UserDto.class);

		//		UserDto userDto=new UserDto();
		//		userDto.setAbout(user.getAbout());
		//		userDto.setName(user.getName());
		//		userDto.setEmail(user.getEmail());
		//		userDto.setPassword(user.getPassword());
		return userDto;

	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto,User.class);
		user.setPassword(this.passwordEncode.encode(userDto.getPassword()));


		Role role = this.roleRepo.findById(AppConstant.NORMAL_USER).get();
		user.getRole().add(role);

		User saveUser = this.userRepo.save(user);
		return this.modelMapper.map(saveUser, UserDto.class);
	}
}
