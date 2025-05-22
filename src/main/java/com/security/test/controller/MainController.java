package com.security.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.security.test.entity.UserInfo;
import com.security.test.repository.UserInfoRepository;

@RestController
@RequestMapping("/users")
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserInfoRepository repository;

	@PostMapping("/addUser")
	public ResponseEntity<?> addUserData(@RequestBody UserInfo userInfo) {
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		UserInfo savedUser = repository.save(userInfo);
		logger.info("User saved successfully : {}", savedUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		logger.error("Error occurred: {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	}

}
