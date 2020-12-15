package com.dynamicformgeneration.models;

import javax.validation.constraints.NotNull;

public class UserModel {

	@NotNull
	private String username;

	@NotNull
	private String password;
	
	public UserModel() {
	}

	public UserModel(@NotNull String username, @NotNull String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
