package com.elearning.courseservice.repository;

import com.elearning.courseservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructorId(String instructorId);
}
