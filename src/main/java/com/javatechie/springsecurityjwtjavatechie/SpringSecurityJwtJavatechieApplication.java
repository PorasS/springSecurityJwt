package com.javatechie.springsecurityjwtjavatechie;

import com.javatechie.springsecurityjwtjavatechie.entities.User;
import com.javatechie.springsecurityjwtjavatechie.repositories.UserRepository;
import com.javatechie.springsecurityjwtjavatechie.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringSecurityJwtJavatechieApplication {

	@Autowired
	UserRepository userRepository;

	@PostConstruct
	public void initUsers(){
		List<User> user=Stream.of(
				new User("pratik", "pratik@bb.com","pratik"),
				new User("shital", "shital@bb.com","shital"),
				new User("katrina", "katrina@bb.com","katrina")
		).collect(Collectors.toList());

		userRepository.saveAll(user);
	}

//	@Bean
//	public JwtUtil jwtUtil(){
//		return new JwtUtil();
//	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtJavatechieApplication.class, args);
	}

}
