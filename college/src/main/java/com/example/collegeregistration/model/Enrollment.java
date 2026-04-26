package com.example.collegeregistration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id;

    @ManyToOne(optional = false)
    @MapsId("studentId")
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnoreProperties("enrollments")
    private Student student;

    @ManyToOne(optional = false)
    @MapsId("courseId")
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnoreProperties("enrollments")
    private Course course;

    private LocalDate enrollmentDate;

    private String grade;

    private Double attendancePercentage;

    public EnrollmentId getId() {
        return id;
    }

    public void setId(EnrollmentId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(Double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
