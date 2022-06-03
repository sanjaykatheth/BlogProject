package com.blog.blogging.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigure {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				     .apiInfo(getInfo()).select().apis(RequestHandlerSelectors.any())
				     .paths(PathSelectors.any()).build();
		
	}

	private ApiInfo getInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("Blog Application", "Develop By Sanjay", "1.0", 
				"Term of Service", new Contact("sanjay", "https://sanjaykatheth.com", "sanjay@gmail.com") 
				, "Licence Of Api", "api", Collections.EMPTY_LIST);
		
	}

}
