package com.elearning.enrollmentservice.service;

import com.elearning.enrollmentservice.client.CourseClient;
import com.elearning.enrollmentservice.model.Enrollment;
import com.elearning.enrollmentservice.repository.EnrollmentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseClient courseClient;

    @CircuitBreaker(name = "courseService", fallbackMethod = "enrollFallback")
    public Enrollment enrollUser(Enrollment enrollment) {
        // Verify course exists
        Object course = courseClient.getCourse(enrollment.getCourseId());
        if (course == null) {
            throw new RuntimeException("Course not found");
        }
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setStatus("ACTIVE");
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment enrollFallback(Enrollment enrollment, Throwable t) {
        // Fallback logic when Course Service is down
        System.out.println("Course Service is down, executing fallback logic for enrollment: " + enrollment);
        // We could queue this request or return a specific status
        enrollment.setStatus("PENDING_VERIFICATION");
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getUserEnrollments(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }
}
