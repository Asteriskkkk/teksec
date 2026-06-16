package com.example.patientmanagement.model;

public class Patient {

	private Long patientId;
	private String name;
	private int age;
	private String disease;

	public Patient(Long patientId, String name, int age, String disease) {
		this.patientId = patientId;
		this.name = name;
		this.age = age;
		this.disease = disease;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}
}
