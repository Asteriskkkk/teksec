package com.example.securityoauth2.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/api/hello")
	public Map<String, Object> hello(Authentication authentication) {
		return Map.of(
			"message", "Hello from the protected resource server",
			"principal", authentication.getName(),
			"authorized", true
		);
	}

	@GetMapping("/")
	public Map<String, String> home() {
		return Map.of(
			"token_endpoint", "POST /oauth2/token",
			"protected_resource", "GET /api/hello"
		);
	}
}
