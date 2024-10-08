package com.expeditors.ecommerce;

import com.expeditors.ecommerce.entities.CustomerEntities;
import com.expeditors.ecommerce.enums.Role;
import com.expeditors.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	public  void run(String... args){
		CustomerEntities adminAccount = userRepository.findByRole(Role.ADMIN);
		if(null== adminAccount){
			CustomerEntities user = new CustomerEntities();

			user.setEmail("naveen@expeditors.com");
			user.setName("Naveen");
			user.setGscId("GSC058");
			user.setPhone("7904222615");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("naveen"));
			userRepository.save(user);
		}
	}
}
