package com.dynamicformgeneration.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynamicformgeneration.models.JwtResponse;
import com.dynamicformgeneration.models.MessageResponse;
import com.dynamicformgeneration.models.UserModel;
import com.dynamicformgeneration.services.IUserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final IUserService userService;

	@Autowired
	public AuthController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody UserModel loginRequest) {
		return new ResponseEntity<>(userService.signin(loginRequest), HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody UserModel model) {
		if (userService.existsByUsername(model.getUsername())) {
			return new ResponseEntity<>(new MessageResponse("Error: Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.signup(model), HttpStatus.CREATED);
	}
}
