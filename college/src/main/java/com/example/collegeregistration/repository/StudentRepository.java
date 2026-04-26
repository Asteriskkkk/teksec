package com.example.collegeregistration.repository;

import com.example.collegeregistration.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
