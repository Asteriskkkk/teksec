package com.example.patientmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.patientmanagement.exception.PatientNotFoundException;
import com.example.patientmanagement.model.Patient;

@Service
public class PatientService {

	private final List<Patient> patients = List.of(
		new Patient(101L, "Anita", 34, "Fever"),
		new Patient(102L, "Rahul", 29, "Cold"),
		new Patient(103L, "Sneha", 41, "Diabetes")
	);

	public Patient getPatientById(Long patientId) {
		return patients.stream()
			.filter(patient -> patient.getPatientId().equals(patientId))
			.findFirst()
			.orElseThrow(() -> new PatientNotFoundException(patientId));
	}
}
