package com.security.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security.test.entity.UserInfo;
import com.security.test.repository.UserInfoRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SpringSecurityTestDemoApplication {

	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	@PostConstruct
	public void init() {
		UserInfo user = new UserInfo();
		user.setName("user");
		user.setPassword(encoder.encode("user"));
		user.setEmail("user@example.com");
		user.setRoles("ROLE_USER");
		repository.save(user);
		System.out.println("Admin initialized: " + user);

		UserInfo admin = new UserInfo();
		admin.setName("admin");
		admin.setPassword(encoder.encode("admin"));
		admin.setEmail("admin@example.com");
		admin.setRoles("ROLE_ADMIN");
		repository.save(admin);
		System.out.println("Admin initialized: " + admin);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityTestDemoApplication.class, args);
	}

}
