package com.springbootblog;

import com.springbootblog.entity.Role;
import com.springbootblog.repository.RoleRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//defining general swagger openAPI information (using annotations)
@OpenAPIDefinition(
		info = @Info(
				title = "SpringBoot Blog REST APIs",
				description = "SpringBoot Blog REST APIs Documentations",
				version = "v1.0",
				contact = @Contact(
						name = "Onyebuchi",
						email = "onyebuchiaja@gmail.com",
						url = "https://www.linkedin.com/in/onyebuchi-aja/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.linkedin.com/in/onyebuchi-aja/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "SpringBoot Blog APP Documentations",
				url = "https://github.com/Aja-buchi/green-glow-blog"
		)
)
//implementing commandlineRunner allows you write a code to insert metadata tables for AWS deployment
public class SpringbootBlogRestApiApplication implements CommandLineRunner {

	//Using ModelMapper
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}
	@Autowired
	private RoleRepository roleRepository;

//	write a code to insert metadata tables for AWS deployment
	@Override
	public void run(String... args) throws Exception {
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN2");
		roleRepository.save(adminRole);

		Role userRole = new Role();
		userRole.setName("USER_ROLE2");
		roleRepository.save(userRole);
		//here
	}
}
