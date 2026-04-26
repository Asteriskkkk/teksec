package com.example.collegeregistration.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "course_id")
    private Long courseId;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String instructor;

    @Column(nullable = false)
    private Integer credits;

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments = new HashSet<>();

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
