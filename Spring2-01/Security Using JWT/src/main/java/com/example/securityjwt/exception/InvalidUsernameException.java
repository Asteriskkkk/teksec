package com.example.securityjwt.exception;

public class InvalidUsernameException extends RuntimeException {

	public InvalidUsernameException(String username) {
		super("Invalid username: " + username);
	}
}
