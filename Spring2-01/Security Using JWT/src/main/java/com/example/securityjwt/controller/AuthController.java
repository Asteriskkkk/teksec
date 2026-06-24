package com.example.securityjwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.securityjwt.dto.AuthRequest;
import com.example.securityjwt.dto.AuthResponse;
import com.example.securityjwt.service.AuthenticationService;

@RestController
public class AuthController {

	private final AuthenticationService authenticationService;

	public AuthController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
		String token = authenticationService.authenticate(request);
		return ResponseEntity.ok(new AuthResponse(token));
	}
}
