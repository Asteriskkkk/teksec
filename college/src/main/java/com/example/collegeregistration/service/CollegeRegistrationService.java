package com.example.collegeregistration.service;

import com.example.collegeregistration.dto.EnrollmentRequest;
import com.example.collegeregistration.model.Course;
import com.example.collegeregistration.model.Enrollment;
import com.example.collegeregistration.model.Student;

import java.time.LocalDate;
import java.util.List;

public interface CollegeRegistrationService {

    Course addCourse(Course course);

    Student addStudent(Student student);

    Enrollment enrollStudent(EnrollmentRequest request);

    Enrollment updateStudentGrade(Long studentId, Long courseId, String grade);

    List<Enrollment> getEnrollmentsByDate(LocalDate enrollmentDate);

    List<Enrollment> getEnrollmentsByInstructor(String instructor);

    Course getMostEnrolledCourse();
}
