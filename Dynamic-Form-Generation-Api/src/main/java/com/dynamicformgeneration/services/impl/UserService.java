package com.dynamicformgeneration.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dynamicformgeneration.config.JwtUtils;
import com.dynamicformgeneration.entities.User;
import com.dynamicformgeneration.models.JwtResponse;
import com.dynamicformgeneration.models.MessageResponse;
import com.dynamicformgeneration.models.UserModel;
import com.dynamicformgeneration.repository.IUserRepository;
import com.dynamicformgeneration.services.IUserService;

@Service
public class UserService implements IUserService {

	private final IUserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	@Autowired
	public UserService(IUserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public UserModel create(UserModel model) {
		User user = new User();
		user.setCreatedDate(new Date());
		user.setModifiedDate(new Date());
		user.setPassword(model.getPassword());
		user.setUsername(model.getUsername());
		user = userRepository.save(user);
		return null;
	}

	@Override
	public UserModel findByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			UserModel model = new UserModel();
			model.setUsername(user.get().getUsername());
			return model;
		}
		return null;
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public JwtResponse signin(UserModel model) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(model.getUsername(), model.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);

		return new JwtResponse(token);
	}	

	@Override
	public MessageResponse signup(UserModel model) {
		create(model);
		return new MessageResponse("User registered successfully!");
	}

}
