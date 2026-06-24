package com.example.securityjwt.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securityjwt.dto.AuthRequest;
import com.example.securityjwt.util.JwtUtil;

@Service
public class AuthenticationService {

	private final CustomUserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public AuthenticationService(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder,
		JwtUtil jwtUtil) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	public String authenticate(AuthRequest request) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
		return jwtUtil.generateToken(userDetails);
	}
}
