package com.example.collegeregistration.service;

import com.example.collegeregistration.dto.EnrollmentRequest;
import com.example.collegeregistration.exception.ResourceNotFoundException;
import com.example.collegeregistration.model.Course;
import com.example.collegeregistration.model.Enrollment;
import com.example.collegeregistration.model.EnrollmentId;
import com.example.collegeregistration.model.Student;
import com.example.collegeregistration.repository.CourseRepository;
import com.example.collegeregistration.repository.EnrollmentRepository;
import com.example.collegeregistration.repository.StudentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CollegeRegistrationServiceImpl implements CollegeRegistrationService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CollegeRegistrationServiceImpl(CourseRepository courseRepository,
                                          StudentRepository studentRepository,
                                          EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Enrollment enrollStudent(EnrollmentRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student ID not found: " + request.getStudentId()));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course ID not found: " + request.getCourseId()));

        EnrollmentId enrollmentId = new EnrollmentId(student.getStudentId(), course.getCourseId());

        Enrollment enrollment = new Enrollment();
        enrollment.setId(enrollmentId);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(request.getEnrollmentDate() != null ? request.getEnrollmentDate() : LocalDate.now());
        enrollment.setGrade(request.getGrade());
        enrollment.setAttendancePercentage(request.getAttendancePercentage());

        return enrollmentRepository.save(enrollment);
    }

    @Override
    @Transactional
    public Enrollment updateStudentGrade(Long studentId, Long courseId, String grade) {
        studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student ID not found: " + studentId));

        courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course ID not found: " + courseId));

        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Enrollment not found for student ID " + studentId + " and course ID " + courseId));

        enrollment.setGrade(grade);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getEnrollmentsByDate(LocalDate enrollmentDate) {
        return enrollmentRepository.findByEnrollmentDate(enrollmentDate);
    }

    @Override
    public List<Enrollment> getEnrollmentsByInstructor(String instructor) {
        return enrollmentRepository.findByInstructor(instructor);
    }

    @Override
    public Course getMostEnrolledCourse() {
        List<Course> courses = courseRepository.findMostEnrolledCourse(PageRequest.of(0, 1));
        if (courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses available");
        }
        return courses.get(0);
    }
}
