package com.example.securityjwt.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@GetMapping("/student/getStudent")
	public ResponseEntity<Map<String, Object>> getStudent() {
		return ResponseEntity.ok(Map.of(
			"id", 101,
			"name", "John Student",
			"course", "Spring Security"
		));
	}
}
