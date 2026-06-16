package com.example.patientmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException {

	public PatientNotFoundException(Long patientId) {
		super("Patient not found with id: " + patientId);
	}
}
