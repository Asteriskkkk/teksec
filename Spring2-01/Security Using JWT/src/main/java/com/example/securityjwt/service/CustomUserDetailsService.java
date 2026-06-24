package com.example.securityjwt.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.securityjwt.exception.InvalidUsernameException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private static final String USERNAME = "john";
	private static final String PASSWORD_HASH = "$2a$10$1pVWw3csE.98E42E6qZoz.NEVQz5H0qEaZhugjXZjYyKTxPl/OG2a";

	@Override
	public UserDetails loadUserByUsername(String username) {
		if (!USERNAME.equals(username)) {
			throw new InvalidUsernameException(username);
		}
		return new User(USERNAME, PASSWORD_HASH, Collections.emptyList());
	}
}
