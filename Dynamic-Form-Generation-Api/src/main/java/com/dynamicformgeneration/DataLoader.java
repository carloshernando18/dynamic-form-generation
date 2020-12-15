package com.dynamicformgeneration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dynamicformgeneration.models.UserModel;
import com.dynamicformgeneration.services.IUserService;

@Component
@Order(Integer.MIN_VALUE)
public class DataLoader implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(DataLoader.class);
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "123456";

	private final PasswordEncoder passwordEncoder;
	private final IUserService userService;

	@Autowired
	public DataLoader(PasswordEncoder passwordEncoder, IUserService userService) {
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("===========================================");
		if (!userService.existsByUsername(USERNAME)) {
			UserModel model = new UserModel();
			model.setUsername(USERNAME);
			model.setPassword(passwordEncoder.encode(PASSWORD));
			userService.create(model);
		}
		logger.info("USER: " + USERNAME);
		logger.info("PASSWORD: " + PASSWORD);
		logger.info("===========================================");
	}

}
