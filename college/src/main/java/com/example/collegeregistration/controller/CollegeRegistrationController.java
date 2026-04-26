package com.example.collegeregistration.controller;

import com.example.collegeregistration.dto.EnrollmentRequest;
import com.example.collegeregistration.dto.GradeUpdateRequest;
import com.example.collegeregistration.model.Course;
import com.example.collegeregistration.model.Enrollment;
import com.example.collegeregistration.model.Student;
import com.example.collegeregistration.service.CollegeRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CollegeRegistrationController {

    private final CollegeRegistrationService collegeRegistrationService;

    public CollegeRegistrationController(CollegeRegistrationService collegeRegistrationService) {
        this.collegeRegistrationService = collegeRegistrationService;
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course created = collegeRegistrationService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student created = collegeRegistrationService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/enrollments")
    public ResponseEntity<Enrollment> enrollStudent(@RequestBody EnrollmentRequest request) {
        Enrollment enrollment = collegeRegistrationService.enrollStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
    }

    @PutMapping("/enrollments/grade")
    public ResponseEntity<Enrollment> updateGrade(@RequestBody GradeUpdateRequest request) {
        Enrollment updated = collegeRegistrationService.updateStudentGrade(
                request.getStudentId(),
                request.getCourseId(),
                request.getGrade()
        );
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/enrollments/date/{enrollmentDate}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByDate(@PathVariable LocalDate enrollmentDate) {
        return ResponseEntity.ok(collegeRegistrationService.getEnrollmentsByDate(enrollmentDate));
    }

    @GetMapping("/enrollments/instructor/{instructor}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByInstructor(@PathVariable String instructor) {
        return ResponseEntity.ok(collegeRegistrationService.getEnrollmentsByInstructor(instructor));
    }

    @GetMapping("/courses/most-enrolled")
    public ResponseEntity<Course> getMostEnrolledCourse() {
        return ResponseEntity.ok(collegeRegistrationService.getMostEnrolledCourse());
    }
}
