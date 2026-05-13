package com.example.collegeregistration.repository;

import com.example.collegeregistration.model.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c left join c.enrollments e group by c order by count(e) desc")
    List<Course> findMostEnrolledCourse(Pageable pageable);
}
