package com.dynamicformgeneration.services;

import com.dynamicformgeneration.models.JwtResponse;
import com.dynamicformgeneration.models.MessageResponse;
import com.dynamicformgeneration.models.UserModel;

public interface IUserService {

	UserModel create(UserModel model);
	
	UserModel findByUsername(String username);

	boolean existsByUsername(String username);
	
	JwtResponse signin(UserModel model);
	
	MessageResponse signup(UserModel model);

}
