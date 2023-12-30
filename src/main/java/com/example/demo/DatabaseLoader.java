package com.example.demo;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DatabaseLoader {
	
	private UserRepository userRepo;
	
	public DatabaseLoader(UserRepository userRepo) {
		this.userRepo = userRepo;
		
	}



	@Bean
	public CommandLineRunner initializeDatabase() {
		return args -> {
			User user1 = new User("oscar@gmail.com","oscar123", Role.ADMIN);
			User user2 = new User("david@gmail.com","david123", Role.ADMIN );
			User user3 = new User("alex@gmail.com","alex123", Role.USER);
			User user4 = new User("mary@gmail.com","mary123",Role.USER);
			
			userRepo.saveAll(List.of(user1,user2,user3,user4));
			
			System.out.println("sample database iko fiti");
		};
	}
	
	
}
