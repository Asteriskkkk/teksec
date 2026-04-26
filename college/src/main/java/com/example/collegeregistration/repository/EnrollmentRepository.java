package com.example.collegeregistration.repository;

import com.example.collegeregistration.model.Enrollment;
import com.example.collegeregistration.model.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    List<Enrollment> findByEnrollmentDate(LocalDate enrollmentDate);

    @Query("select e from Enrollment e where e.course.instructor = :instructor")
    List<Enrollment> findByInstructor(String instructor);
}
