package com.blog.blogging;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.blogging.entities.Role;
import com.blog.blogging.payloads.AppConstant;
import com.blog.blogging.repositories.RoleRepository;

@SpringBootApplication
public class BlogProjectApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder paswordEnc;
	 
	@Autowired
	private RoleRepository roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogProjectApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();	
	}

	@Override
	public void run(String... args) throws Exception {
	try {
		Role roleUser=new Role();
		roleUser.setId(AppConstant.ADMIN_USER);
		roleUser.setName("ADMIN_USER");
		this.roleRepo.save(roleUser);
		Role rolenNormal=new Role();
		rolenNormal.setId(AppConstant.NORMAL_USER);
		rolenNormal.setName("NORMAL_USER");
		this.roleRepo.save(rolenNormal);

		
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	}

} 
