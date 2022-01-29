package io.getarrays.userservices;

import io.getarrays.userservices.domains.Role;
import io.getarrays.userservices.domains.User;
import io.getarrays.userservices.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserservicesApplication.class, args);
	}

	@Bean
	CommandLineRunner runner (UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null , "John Travolta","john","1234",new ArrayList<Role>()));
			userService.saveUser(new User(null , "Will Smith","Will","1234",new ArrayList<Role>()));
			userService.saveUser(new User(null , "Toni Jaa","toni","1234",new ArrayList<Role>()));
			userService.saveUser(new User(null , "Arnold Swaznagar","arnold","1234",new ArrayList<Role>()));

			userService.addRoleToUser("john","ROLE_USER");
			userService.addRoleToUser("john","ROLE_MANAGER");
			userService.addRoleToUser("Will","ROLE_MANAGER");
			userService.addRoleToUser("toni","ROLE_ADMIN");
			userService.addRoleToUser("arnold","ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arnold","ROLE_ADMIN");
			userService.addRoleToUser("arnold","ROLE_USER");
		};
	}

	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
